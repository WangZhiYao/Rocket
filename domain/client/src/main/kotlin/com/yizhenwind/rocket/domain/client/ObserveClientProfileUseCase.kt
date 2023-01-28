package com.yizhenwind.rocket.domain.client

import androidx.paging.PagingData
import com.yizhenwind.rocket.data.client.ClientRepository
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ObserveClientProfileUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<PagingData<ClientProfile>> =
        clientRepository.observeClientProfile().flowOn(dispatcher)

}