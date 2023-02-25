package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.framework.mvi.IViewState

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
data class CreateClientViewState(
    val contactTypeList: List<ContactType> = ContactType.values().toList(),
    val contactType: ContactType = ContactType.QQ
) : IViewState