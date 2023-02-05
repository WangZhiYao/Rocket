package com.yizhenwind.rocket.ui.client

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
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
 * @since 2023/1/16
 */
@HiltViewModel
class ClientProfileViewModel @Inject constructor(
    private val clientService: IClientService
) : BaseMVIViewModel<ClientProfileViewState, Nothing>() {

    override val container = container<ClientProfileViewState, Nothing>(ClientProfileViewState())

    init {
        intent {
            clientService.observeClientProfile()
                .collect {
                    reduce {
                        state.copy(clientProfileList = it)
                    }
                }
        }
    }

}