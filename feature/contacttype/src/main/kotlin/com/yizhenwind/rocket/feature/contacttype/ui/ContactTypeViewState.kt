package com.yizhenwind.rocket.feature.contacttype.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
data class ContactTypeViewState(val contactTypeList: List<ContactType> = emptyList()) : IViewState