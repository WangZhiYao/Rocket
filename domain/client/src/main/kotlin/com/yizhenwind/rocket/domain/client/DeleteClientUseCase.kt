package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.data.client.ClientRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/19
 */
class DeleteClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(client: Client): Flow<Int> =
        clientRepository.deleteClient(client).flowOn(dispatcher)

}