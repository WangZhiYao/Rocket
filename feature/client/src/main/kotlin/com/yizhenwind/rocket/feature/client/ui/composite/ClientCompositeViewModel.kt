package com.yizhenwind.rocket.feature.client.ui.composite

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.core.infra.logger.Logger
import com.yizhenwind.rocket.domain.client.usecase.DeleteClientUseCase
import com.yizhenwind.rocket.domain.client.usecase.GetClientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/1
 */
@HiltViewModel
class ClientCompositeViewModel @Inject constructor(
    private val getClientByIdUseCase: GetClientByIdUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    private val logger: Logger
) : BaseMVIViewModel<ClientCompositeViewState, ClientCompositeSideEffect>() {

    override val container =
        container<ClientCompositeViewState, ClientCompositeSideEffect>(ClientCompositeViewState())

    val client: Client
        get() = container.stateFlow.value.client

    fun getClientById(clientId: Long) {
        intent {
            getClientByIdUseCase(clientId)
                .collect { client ->
                    reduce {
                        state.copy(client = client)
                    }
                }
        }
    }

    fun attemptDeleteClient(client: Client) {
        intent {
            deleteClientUseCase(client)
                .catch {
                    logger.e(it)
                    emit(false)
                }
                .collect { success ->
                    if (success) {
                        postSideEffect(ClientCompositeSideEffect.DeleteClientSuccess)
                    } else {
                        postSideEffect(ClientCompositeSideEffect.DeleteClientFailed)
                    }
                }
        }
    }
}