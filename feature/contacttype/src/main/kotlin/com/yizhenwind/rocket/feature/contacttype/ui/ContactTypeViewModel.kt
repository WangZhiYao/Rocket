package com.yizhenwind.rocket.feature.contacttype.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.domain.contacttype.ObserveContactTypeUseCase
import com.yizhenwind.rocket.domain.contacttype.UpdateContactTypeUseCase
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
    private val updateContactTypeUseCase: UpdateContactTypeUseCase
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

    fun updateContactType(contactType: ContactType) {
        intent {
            updateContactTypeUseCase(contactType)
                .collect { contactType ->
                    if (!contactType.enable) {
                        postSideEffect(ContactTypeSideEffect.DeleteContactTypeSuccess(contactType))
                    }
                }
        }
    }

}