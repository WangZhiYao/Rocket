package com.yizhenwind.rocket.feature.client.ui.create

import androidx.lifecycle.viewModelScope
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.contacttype.IContactTypeService
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.domain.client.CheckIfClientExistByContactUseCase
import com.yizhenwind.rocket.domain.client.CreateClientUseCase
import com.yizhenwind.rocket.feature.client.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val contactTypeService: IContactTypeService,
    private val checkIfClientExistByContactUseCase: CheckIfClientExistByContactUseCase,
    private val createClientUseCase: CreateClientUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateClientViewState, CreateClientSideEffect>() {

    override val container =
        container<CreateClientViewState, CreateClientSideEffect>(CreateClientViewState())

    private val _contactTypeList: MutableStateFlow<List<ContactType>> =
        MutableStateFlow(emptyList())
    val contactTypeList: StateFlow<List<ContactType>> get() = _contactTypeList

    private val _contactType: MutableStateFlow<ContactType> = MutableStateFlow(ContactType())
    val contactType: StateFlow<ContactType> get() = _contactType

    init {
        viewModelScope.launch {
            contactTypeService.observeContactType()
                .collect { contactTypeList ->
                    _contactTypeList.update { contactTypeList }
                    if (contactTypeList.isNotEmpty()) {
                        _contactType.update { contactTypeList[0] }
                    }
                }
        }
    }

    fun onNameChanged(name: String?) {
        intent {
            if (!name.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.HideNameError)
                return@intent
            }
        }
    }

    fun onContactTypeChanged(position: Int) {
        viewModelScope.launch {
            if (position >= 0 && position < contactTypeList.value.size) {
                _contactType.update { contactTypeList.value[position] }
            }
        }
    }

    fun onContactChanged(contact: String?) {
        intent {
            if (!contact.isNullOrBlank()) {
                postSideEffect(CreateClientSideEffect.HideContactError)
                if (checkIfClientExistByContactUseCase(contactType.value, contact)) {
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

            if (checkIfClientExistByContactUseCase(contactType.value, contact)) {
                postSideEffect(CreateClientSideEffect.ShowContactError(R.string.error_client_exist))
                return@intent
            }

            createClientUseCase(
                Client(
                    name = name,
                    contactType = contactType.value,
                    contact = contact,
                    remark = remark
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
                        if (contactTypeList.value.isNotEmpty()) {
                            _contactType.update { contactTypeList.value[0] }
                        }
                    }
                }
        }
    }
}