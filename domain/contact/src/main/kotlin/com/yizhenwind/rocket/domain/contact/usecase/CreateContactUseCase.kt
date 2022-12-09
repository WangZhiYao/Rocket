package com.yizhenwind.rocket.domain.contact.usecase

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.model.Contact
import com.yizhenwind.rocket.domain.contact.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class CreateContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    operator fun invoke(clientId: Long, contactType: ContactType, value: String): Flow<Contact> =
        contactRepository.createContact(clientId, contactType, value)

}