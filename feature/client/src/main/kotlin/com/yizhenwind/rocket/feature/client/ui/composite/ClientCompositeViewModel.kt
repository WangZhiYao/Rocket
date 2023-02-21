package com.yizhenwind.rocket.feature.client.ui.composite

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.domain.client.DeleteClientUseCase
import com.yizhenwind.rocket.feature.client.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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
class ClientCompositeViewModel @Inject constructor(
    private val deleteClientUseCase: DeleteClientUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<ClientCompositeViewState, ClientCompositeSideEffect>() {

    override val container =
        container<ClientCompositeViewState, ClientCompositeSideEffect>(ClientCompositeViewState())

    val client: Client
        get() = container.stateFlow.value.client

    fun setClient(client: Client) {
        intent {
            reduce {
                state.copy(client = client)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(ClientCompositeSideEffect.ShowError(resId))
        }
    }

    fun attemptDeleteClient(client: Client) {
        intent {
            deleteClientUseCase(client)
                .catch {
                    logger.e(it)
                    postSideEffect(ClientCompositeSideEffect.ShowError(R.string.error_delete_client_failed))
                }
                .collect {
                    postSideEffect(ClientCompositeSideEffect.DeleteClientSuccess)
                }
        }
    }
}