package com.yizhenwind.rocket.feature.subject.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.subject.ObserveSubjectListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
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
class SubjectListViewModel @Inject constructor(
    private val observeSubjectListUseCase: ObserveSubjectListUseCase
) : BaseMVIViewModel<SubjectListViewState, Nothing>() {

    override val container =
        container<SubjectListViewState, Nothing>(SubjectListViewState())

    init {
        intent {
            observeSubjectListUseCase()
                .collect { subjectList ->
                    reduce {
                        state.copy(subjectList = subjectList)
                    }
                }
        }
    }

}