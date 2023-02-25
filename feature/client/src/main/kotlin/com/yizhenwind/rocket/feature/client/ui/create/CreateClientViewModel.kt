package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.domain.client.CheckIfClientExistByContactUseCase
import com.yizhenwind.rocket.domain.client.CreateClientUseCase
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
 *
 * @author WangZhiYao
 * @since 2022/11/29
 */
@HiltViewModel
class CreateClientViewModel @Inject constructor(
    private val checkIfClientExistByContactUseCase: CheckIfClientExistByContactUseCase,
    private val createClientUseCase: CreateClientUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateClientViewState, CreateClientSideEffect>() {

    override val container =
        container<CreateClientViewState, CreateClientSideEffect>(CreateClientViewState())

    fun onNameChanged(name: String?) {
        intent {
            postSideEffect(CreateClientSideEffect.HideNameError)
        }
    }

    fun onContactTypeChanged(contactType: ContactType) {
        intent {
            reduce {
                state.copy(contactType = contactType)
            }
        }
    }

    fun onContactChanged(contact: String?) {
        intent {
            postSideEffect(CreateClientSideEffect.HideContactError)
            if (!contact.isNullOrBlank()) {
                if (checkIfClientExistByContactUseCase(state.contactType, contact)) {
                    postSideEffect(CreateClientSideEffect.ShowContactError(R.string.error_client_exist))
                }
            }
        }
    }

    fun attemptCreateClient(name: String?, contact: String?, remark: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.ShowNameError(R.string.error_client_name_empty))
                return@intent
            }

            if (contact.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.ShowContactError(R.string.error_contact_empty))
                return@intent
            }

            if (checkIfClientExistByContactUseCase(state.contactType, contact)) {
                postSideEffect(CreateClientSideEffect.ShowContactError(R.string.error_client_exist))
                return@intent
            }

            createClientUseCase(
                Client(
                    name = name,
                    contactType = state.contactType,
                    contact = contact,
                    remark = remark.ifNull { "" }
                )
            )
                .catch {
                    logger.e(it)
                    emit(Client())
                }
                .collect { client ->
                    if (client.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreateClientSideEffect.CreateClientFailure(R.string.error_create_client))
                    } else {
                        postSideEffect(CreateClientSideEffect.CreateClientSuccess(client))
                    }
                }
        }
    }
}