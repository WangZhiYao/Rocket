package com.yizhenwind.rocket.feature.client.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.ObserveClientListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val observeClientListUseCase: ObserveClientListUseCase
) : BaseMVIViewModel<ClientListViewState, Nothing>() {

    override val container = container<ClientListViewState, Nothing>(
        ClientListViewState()
    )

    init {
        intent {
            observeClientListUseCase()
                .collect {
                    reduce {
                        state.copy(clientList = it)
                    }
                }
        }
    }

}