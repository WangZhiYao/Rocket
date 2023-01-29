package com.yizhenwind.rocket.feature.contacttype.ui

import com.yizhenwind.rocket.core.model.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
sealed class ContactTypeSideEffect {

    data class DeleteContactTypeSuccess(val contactType: ContactType) : ContactTypeSideEffect()

}