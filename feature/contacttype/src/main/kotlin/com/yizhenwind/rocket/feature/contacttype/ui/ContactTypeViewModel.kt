package com.yizhenwind.rocket.feature.contacttype.ui

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.contacttype.CreateOrEnableContactTypeUseCase
import com.yizhenwind.rocket.domain.contacttype.GetContactTypeByNameUseCase
import com.yizhenwind.rocket.domain.contacttype.ObserveContactTypeUseCase
import com.yizhenwind.rocket.feature.contacttype.R
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@HiltViewModel
class ContactTypeViewModel @Inject constructor(
    private val observeContactTypeUseCase: ObserveContactTypeUseCase,
    private val getContactTypeByNameUseCase: GetContactTypeByNameUseCase,
    private val createOrEnableContactTypeUseCase: CreateOrEnableContactTypeUseCase
) : BaseMVIViewModel<ContactTypeViewState, ContactTypeSideEffect>() {

    override val container =
        container<ContactTypeViewState, ContactTypeSideEffect>(ContactTypeViewState())

    init {
        intent {
            observeContactTypeUseCase()
                .collect { contactTypeList ->
                    reduce {
                        state.copy(contactTypeList = contactTypeList)
                    }
                }
        }
    }

    fun onNameChanged(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(ContactTypeSideEffect.HideError)
                return@intent
            }

            val contactType = getContactTypeByNameUseCase(name)
            if (contactType.id == Constant.DEFAULT_ID || !contactType.enable) {
                postSideEffect(ContactTypeSideEffect.HideError)
            } else {
                postSideEffect(ContactTypeSideEffect.ShowError(R.string.error_contact_type_exist))
            }
        }
    }

    fun createContactType(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(ContactTypeSideEffect.ShowError(R.string.error_contact_type_empty))
                return@intent
            }
            createOrEnableContactTypeUseCase(name)
                .collect {
                    postSideEffect(ContactTypeSideEffect.ShowSnake(R.string.contact_type_create_success))
                }
        }
    }
}