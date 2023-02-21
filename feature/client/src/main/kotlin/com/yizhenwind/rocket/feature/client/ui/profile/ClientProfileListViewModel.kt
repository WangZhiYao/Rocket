package com.yizhenwind.rocket.feature.client.ui.profile

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.ObserveClientProfileListUseCase
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
class ClientProfileListViewModel @Inject constructor(
    private val observeClientProfileListUseCase: ObserveClientProfileListUseCase
) : BaseMVIViewModel<ClientProfileListViewState, Nothing>() {

    override val container = container<ClientProfileListViewState, Nothing>(ClientProfileListViewState())

    init {
        intent {
            observeClientProfileListUseCase()
                .collect {
                    reduce {
                        state.copy(clientProfileList = it)
                    }
                }
        }
    }

}