package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ContactType

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
data class CreateClientViewState(
    val contactTypeList: List<ContactType> = emptyList(),
    val contactType: ContactType = ContactType()
) : IViewState