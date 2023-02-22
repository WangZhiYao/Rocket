package com.yizhenwind.rocket.feature.subject.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.pickFirstOrDefault
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.domain.subject.CreateSubjectUseCase
import com.yizhenwind.rocket.feature.subject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
@HiltViewModel
class CreateSubjectViewModel @Inject constructor(
    private val categoryService: ICategoryService,
    private val createSubjectUseCase: CreateSubjectUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateSubjectViewState, CreateSubjectSideEffect>() {

    override val container =
        container<CreateSubjectViewState, CreateSubjectSideEffect>(CreateSubjectViewState())

    fun initViewState(categoryId: Long) {
        intent {
            categoryService.observeCategoryList()
                .collect { categoryList ->
                    reduce {
                        state.copy(
                            categoryList = categoryList,
                            category = categoryList.pickFirstOrDefault(Category()) { it.id == categoryId })
                    }
                }
        }
    }

    fun onCategorySelected(category: Category) {
        intent {
            postSideEffect(CreateSubjectSideEffect.HideCategoryError)
            reduce {
                state.copy(category = category)
            }
        }
    }

    fun onContentChanged(content: String?) {
        intent {
            postSideEffect(CreateSubjectSideEffect.HideContentError)
        }
    }

    fun attemptCreateSubject(content: String?) {
        intent {
            if (content.isNullOrBlank()) {
                postSideEffect(CreateSubjectSideEffect.ShowContentError(R.string.error_create_subject_empty_content))
                return@intent
            }
            createSubjectUseCase(Subject(category = state.category, content = content))
                .catch {
                    logger.e(it)
                    emit(Subject())
                }
                .collect { subject ->
                    if (subject.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreateSubjectSideEffect.CreateSubjectFailed(R.string.error_create_subject))
                    } else {
                        postSideEffect(CreateSubjectSideEffect.CreateSubjectSuccess(subject))
                    }
                }
        }
    }
}