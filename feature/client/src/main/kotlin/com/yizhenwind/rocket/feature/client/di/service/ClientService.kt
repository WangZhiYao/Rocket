package com.yizhenwind.rocket.feature.client.di.service

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.domain.client.usecase.ObserveClientProfileListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */

class ClientService @Inject constructor(
    private val observeClientProfileListUseCase: ObserveClientProfileListUseCase
) : IClientService {

    override fun observeClientProfileList(): Flow<PagingData<ClientProfile>> =
        observeClientProfileListUseCase()

}