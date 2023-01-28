package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.data.client.ClientRepository
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ContactType
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
class GetClientByContactUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    suspend operator fun invoke(contactType: ContactType, contact: String): Client? =
        clientRepository.getClientByContact(contactType, contact)
}