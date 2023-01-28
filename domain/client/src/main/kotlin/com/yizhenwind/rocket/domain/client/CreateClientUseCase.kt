package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.data.client.ClientRepository
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.Client
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
class CreateClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(client: Client): Flow<Client> =
        clientRepository.createClient(client)
            .flowOn(dispatcher)

}