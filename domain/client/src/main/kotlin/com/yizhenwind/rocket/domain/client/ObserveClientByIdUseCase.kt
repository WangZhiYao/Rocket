package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.data.client.ClientRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
class ObserveClientByIdUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(id: Long): Flow<Client> =
        clientRepository.observeClientById(id)
            .map { client -> client.ifNull { Client() } }
            .flowOn(dispatcher)

}