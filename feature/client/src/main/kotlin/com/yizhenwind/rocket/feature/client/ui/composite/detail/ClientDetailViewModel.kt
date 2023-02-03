package com.yizhenwind.rocket.feature.client.ui.composite.detail

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.ObserveClientByIdUseCase
import com.yizhenwind.rocket.feature.client.R
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
@HiltViewModel
class ClientDetailViewModel @Inject constructor(
    private val observeClientByIdUseCase: ObserveClientByIdUseCase
) : BaseMVIViewModel<ClientDetailViewState, ClientDetailSideEffect>() {

    override val container =
        container<ClientDetailViewState, ClientDetailSideEffect>(ClientDetailViewState())

    val contact: String
        get() = container.stateFlow.value.client.contact

    fun observeClientById(id: Long) {
        intent {
            observeClientByIdUseCase(id)
                .collect { client ->
                    if (client.id == Constant.DEFAULT_ID) {
                        postSideEffect(ClientDetailSideEffect(R.string.error_client_detail))
                    } else {
                        reduce {
                            state.copy(client = client)
                        }
                    }
                }
        }
    }
}