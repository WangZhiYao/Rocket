package com.yizhenwind.rocket.domain.client.usecase

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.domain.client.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class DeleteClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(client: Client): Flow<Boolean> =
        clientRepository.deleteClient(client)

}