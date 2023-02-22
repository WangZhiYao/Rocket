package com.yizhenwind.rocket.feature.category.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.domain.category.CreateCategoryUseCase
import com.yizhenwind.rocket.feature.category.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateCategoryViewState, CreateCategorySideEffect>() {

    override val container =
        container<CreateCategoryViewState, CreateCategorySideEffect>(CreateCategoryViewState())

    fun onTitleChanged(title: String?) {
        intent {
            postSideEffect(CreateCategorySideEffect.HideTitleError)
        }
    }

    fun createCategory(title: String?, remark: String?) {
        intent {
            if (title.isNullOrBlank()) {
                postSideEffect(CreateCategorySideEffect.ShowTitleError(R.string.error_create_category_empty_title))
                return@intent
            }

            createCategoryUseCase(Category(title = title, remark = remark.ifNull { "" }))
                .catch {
                    logger.e(it)
                    emit(Category())
                }
                .collect { category ->
                    if (category.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreateCategorySideEffect.ShowSnack(R.string.error_create_category_failed))
                    } else {
                        postSideEffect(CreateCategorySideEffect.CreateCategorySuccess(category))
                    }
                }
        }
    }
}