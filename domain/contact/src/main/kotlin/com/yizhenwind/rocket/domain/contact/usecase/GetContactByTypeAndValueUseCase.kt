package com.yizhenwind.rocket.domain.contact.usecase

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.model.Contact
import com.yizhenwind.rocket.domain.contact.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/8
 */
class GetContactByTypeAndValueUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    operator fun invoke(contactType: ContactType, value: String): Flow<Contact> =
        contactRepository.getContactByTypeAndValue(contactType, value)

}