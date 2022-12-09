package com.yizhenwind.rocket.feature.contact.di.service

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.model.Contact
import com.yizhenwind.rocket.core.mediator.contact.IContactService
import com.yizhenwind.rocket.domain.contact.usecase.CreateContactUseCase
import com.yizhenwind.rocket.domain.contact.usecase.GetContactByTypeAndValueUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class ContactService @Inject constructor(
    private val createContactUseCase: CreateContactUseCase,
    private val getContactByTypeAndValueUseCase: GetContactByTypeAndValueUseCase
) : IContactService {

    override fun createContact(
        clientId: Long,
        contactType: ContactType,
        value: String
    ): Flow<Contact> = createContactUseCase(clientId, contactType, value)

    override fun getContact(contactType: ContactType, value: String): Flow<Contact> =
        getContactByTypeAndValueUseCase(contactType, value)

}