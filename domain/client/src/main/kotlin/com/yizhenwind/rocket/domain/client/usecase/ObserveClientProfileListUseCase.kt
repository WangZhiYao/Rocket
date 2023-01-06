package com.yizhenwind.rocket.domain.client.usecase

import androidx.paging.PagingData
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.domain.client.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/29
 */
class ObserveClientProfileListUseCase @Inject constructor(
    private val clientRepository: ClientRepository
) {

    operator fun invoke(): Flow<PagingData<ClientProfile>> =
        clientRepository.observeClientProfileList()

}