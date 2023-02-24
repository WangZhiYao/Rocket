package com.yizhenwind.rocket.feature.contacttype.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.domain.contacttype.CreateContactTypeUseCase
import com.yizhenwind.rocket.domain.contacttype.GetContactTypeByNameUseCase
import com.yizhenwind.rocket.domain.contacttype.UpdateContactTypeUseCase
import com.yizhenwind.rocket.feature.contacttype.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
@HiltViewModel
class CreateContactTypeViewModel @Inject constructor(
    private val getContactTypeByNameUseCase: GetContactTypeByNameUseCase,
    private val createContactTypeUseCase: CreateContactTypeUseCase,
    private val updateContactTypeUseCase: UpdateContactTypeUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateContactTypeViewState, CreateContactTypeSideEffect>() {

    override val container =
        container<CreateContactTypeViewState, CreateContactTypeSideEffect>(
            CreateContactTypeViewState()
        )

    fun onNameChanged(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateContactTypeSideEffect.HideNameError)
                return@intent
            }

            val contactType = getContactTypeByNameUseCase(name)
            if (contactType.id != Constant.DEFAULT_ID && contactType.enable) {
                postSideEffect(CreateContactTypeSideEffect.ShowNameError(R.string.error_contact_type_exist))
                return@intent
            }

            postSideEffect(CreateContactTypeSideEffect.HideNameError)
        }
    }

    fun createContactType(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateContactTypeSideEffect.ShowNameError(R.string.error_contact_type_empty))
                return@intent
            }
            val contactType = getContactTypeByNameUseCase(name)
            if (contactType.id != Constant.DEFAULT_ID && contactType.enable) {
                postSideEffect(CreateContactTypeSideEffect.ShowNameError(R.string.error_contact_type_exist))
                return@intent
            }

            val upsertFlow = if (contactType.id == Constant.DEFAULT_ID) {
                createContactTypeUseCase(ContactType(name = name))
            } else if (!contactType.enable) {
                updateContactTypeUseCase(contactType.copy(enable = true))
            } else {
                flowOf(ContactType())
            }

            upsertFlow
                .catch {
                    logger.e(it)
                    emit(ContactType())
                }
                .collect {
                    if (it.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreateContactTypeSideEffect.ShowError(R.string.error_create_contact_type))
                    } else {
                        postSideEffect(CreateContactTypeSideEffect.CreateContactTypeSuccess)
                    }
                }
        }
    }
}