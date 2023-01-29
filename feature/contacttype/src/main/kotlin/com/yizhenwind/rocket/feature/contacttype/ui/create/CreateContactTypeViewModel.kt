package com.yizhenwind.rocket.feature.contacttype.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.contacttype.CreateOrEnableContactTypeUseCase
import com.yizhenwind.rocket.domain.contacttype.GetContactTypeByNameUseCase
import com.yizhenwind.rocket.feature.contacttype.R
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val createOrEnableContactTypeUseCase: CreateOrEnableContactTypeUseCase
) : BaseMVIViewModel<CreateContactTypeViewState, CreateContactTypeSideEffect>() {

    override val container =
        container<CreateContactTypeViewState, CreateContactTypeSideEffect>(
            CreateContactTypeViewState()
        )

    fun onNameChanged(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateContactTypeSideEffect.HideError)
                return@intent
            }

            val contactType = getContactTypeByNameUseCase(name)
            if (contactType.id == Constant.DEFAULT_ID || !contactType.enable) {
                postSideEffect(CreateContactTypeSideEffect.HideError)
            } else {
                postSideEffect(CreateContactTypeSideEffect.ShowError(R.string.error_contact_type_exist))
            }
        }
    }

    fun createContactType(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreateContactTypeSideEffect.ShowError(R.string.error_contact_type_empty))
                return@intent
            }
            val contactType = getContactTypeByNameUseCase(name)
            if (contactType.id == Constant.DEFAULT_ID || !contactType.enable) {
                createOrEnableContactTypeUseCase(name)
                    .collect {
                        postSideEffect(CreateContactTypeSideEffect.NavigationUp)
                    }
            } else {
                postSideEffect(CreateContactTypeSideEffect.ShowError(R.string.error_contact_type_exist))
            }
        }
    }
}