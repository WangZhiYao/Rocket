package com.yizhenwind.rocket.domain.client.usecase

import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.domain.client.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class GetClientByIdUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(id: Long): Flow<Client> =
        clientRepository.getClientById(id)
    
}