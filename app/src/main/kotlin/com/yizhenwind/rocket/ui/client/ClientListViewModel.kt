package com.yizhenwind.rocket.ui.client

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.core.mediator.client.IClientService
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
    private val clientService: IClientService
) : BaseMVIViewModel<ClientListViewState, ClientListSideEffect>() {

    override val container =
        container<ClientListViewState, ClientListSideEffect>(ClientListViewState())

    init {
        intent {
            clientService.observeClientProfileList()
                .cachedIn(viewModelScope)
                .collect { clientProfileList ->
                    reduce {
                        state.copy(clientProfileList = clientProfileList)
                    }
                }
        }
    }
}