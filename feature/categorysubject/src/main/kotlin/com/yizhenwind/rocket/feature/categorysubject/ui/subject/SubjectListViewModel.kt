package com.yizhenwind.rocket.feature.categorysubject.ui.subject

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.domain.subject.DeleteSubjectUseCase
import com.yizhenwind.rocket.domain.subject.ObserveSubjectListByCategoryIdUseCase
import com.yizhenwind.rocket.feature.categorysubject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
@HiltViewModel
class SubjectListViewModel @Inject constructor(
    private val observeSubjectListByCategoryIdUseCase: ObserveSubjectListByCategoryIdUseCase,
    private val deleteSubjectUseCase: DeleteSubjectUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<SubjectListViewState, SubjectListSideEffect>() {

    override val container =
        container<SubjectListViewState, SubjectListSideEffect>(SubjectListViewState())

    fun observeSubjectListByCategoryId(categoryId: Long) {
        intent {
            observeSubjectListByCategoryIdUseCase(categoryId)
                .collect { subjectList ->
                    reduce {
                        state.copy(subjectList = subjectList)
                    }
                }
        }
    }

    fun deleteSubject(subject: Subject) {
        intent {
            deleteSubjectUseCase(subject)
                .catch {
                    logger.e(it)
                    postSideEffect(SubjectListSideEffect.ShowError(R.string.error_delete_subject_failed))
                }
                .collect {
                    postSideEffect(SubjectListSideEffect.DeleteSubjectSuccess)
                }
        }
    }
}