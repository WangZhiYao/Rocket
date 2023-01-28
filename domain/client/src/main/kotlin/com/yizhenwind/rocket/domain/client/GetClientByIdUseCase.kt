package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.data.client.ClientRepository
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
class GetClientByIdUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    suspend operator fun invoke(id: Long): Client? =
        clientRepository.getClientById(id)

}