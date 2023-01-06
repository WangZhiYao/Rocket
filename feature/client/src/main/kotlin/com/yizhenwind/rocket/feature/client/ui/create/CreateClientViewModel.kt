package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.framework.mvi.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.contact.IContactService
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.domain.client.usecase.CreateClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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
    private val logger: ILogger,
    private val createClientUseCase: CreateClientUseCase,
    private val contactService: IContactService
) : BaseMVIViewModel<CreateClientViewState, CreateClientSideEffect>() {

    override val container =
        container<CreateClientViewState, CreateClientSideEffect>(CreateClientViewState())

    private val _contactType: MutableStateFlow<ContactType> = MutableStateFlow(ContactType.QQ)
    val contactType: StateFlow<ContactType> get() = _contactType

    init {
        intent {
            _contactType.value = ContactType.QQ
        }
    }

    fun onNameChanged(name: String?) {
        intent {
            if (!name.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.HideClientError)
            }
        }
    }

    fun onContactTypeChanged(contactType: ContactType) {
        intent {
            _contactType.value = contactType
        }
    }

    fun onContactChanged(contact: String?) {
        intent {
            if (contact.isNullOrBlank()) {
                return@intent
            }

            postSideEffect(CreateClientSideEffect.HideContactError)

            contactService.getContact(_contactType.value, contact)
                .collect {
                    if (it.id != 0L) {
                        postSideEffect(CreateClientSideEffect.ShowContactExistError)
                    } else {
                        postSideEffect(CreateClientSideEffect.HideContactError)
                    }
                }
        }
    }

    fun attemptCreateClient(
        name: String?,
        contact: String?,
        remark: String?
    ) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.ShowClientNameEmptyError)
                return@intent
            }

            if (contact.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.ShowContactEmptyError)
                return@intent
            }

            createClientUseCase(name, remark)
                .flatMapConcat { client ->
                    if (client.id != 0L) {
                        contactService.createContact(client.id, _contactType.value, contact)
                            .map {
                                client.copy(contactList = listOf(it))
                            }
                    } else {
                        flowOf(Client())
                    }
                }
                .catch {
                    logger.e(it)
                    postSideEffect(CreateClientSideEffect.CreateClientFailure)
                }
                .collect { client ->
                    postSideEffect(
                        if (client.id != 0L) {
                            CreateClientSideEffect.CreateClientSuccess(client.id)
                        } else {
                            CreateClientSideEffect.CreateClientFailure
                        }
                    )
                }
        }
    }

}

