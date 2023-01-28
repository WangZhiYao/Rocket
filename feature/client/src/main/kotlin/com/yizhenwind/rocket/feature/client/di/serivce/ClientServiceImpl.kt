package com.yizhenwind.rocket.feature.client.di.serivce

import androidx.paging.PagingData
import com.yizhenwind.rocket.domain.client.ObserveClientProfileUseCase
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class ClientServiceImpl @Inject constructor(
    private val observeClientProfileUseCase: ObserveClientProfileUseCase
) : IClientService {

    override fun observeClientProfile(): Flow<PagingData<ClientProfile>> =
        observeClientProfileUseCase()

}