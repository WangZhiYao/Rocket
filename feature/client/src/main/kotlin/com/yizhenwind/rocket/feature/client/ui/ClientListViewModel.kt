package com.yizhenwind.rocket.feature.client.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
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
    private val clientRepository: ClientRepository
) : BaseMVIViewModel<ClientListViewState, ClientListSideEffect>() {

    override val container: Container<ClientListViewState, ClientListSideEffect> =
        container(ClientListViewState())

    init {
        intent {
            clientRepository.observeClientProfileList()
                .cachedIn(viewModelScope)
                .collect { clientProfileList ->
                    reduce {
                        ClientListViewState(clientProfileList)
                    }
                }
        }
    }
}