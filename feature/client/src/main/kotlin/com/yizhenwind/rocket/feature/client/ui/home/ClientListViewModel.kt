package com.yizhenwind.rocket.feature.client.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.usecase.ObserveClientProfileListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val observeClientProfileListUseCase: ObserveClientProfileListUseCase
) : BaseMVIViewModel<ClientListViewState, ClientListSideEffect>() {

    override val container =
        container<ClientListViewState, ClientListSideEffect>(ClientListViewState())

    init {
        intent {
            observeClientProfileListUseCase()
                .cachedIn(viewModelScope)
                .collect { clientProfileList ->
                    reduce {
                        ClientListViewState(clientProfileList)
                    }
                }
        }
    }
}