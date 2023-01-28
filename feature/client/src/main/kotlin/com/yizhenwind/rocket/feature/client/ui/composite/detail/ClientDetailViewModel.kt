package com.yizhenwind.rocket.feature.client.ui.composite.detail

import com.yizhenwind.rocket.core.common.ext.ifNullOrElse
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.client.GetClientByIdUseCase
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
    private val getClientByIdUseCase: GetClientByIdUseCase
) : BaseMVIViewModel<ClientDetailViewState, ClientDetailSideEffect>() {

    override val container =
        container<ClientDetailViewState, ClientDetailSideEffect>(ClientDetailViewState())

    fun getClientById(id: Long) {
        intent {
            getClientByIdUseCase(id).ifNullOrElse({
                postSideEffect(ClientDetailSideEffect(R.string.error_client_id))
            }, { client ->
                reduce {
                    state.copy(client = client)
                }
            })
        }
    }
}