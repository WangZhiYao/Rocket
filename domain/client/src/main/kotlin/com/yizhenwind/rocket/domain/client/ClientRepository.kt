package com.yizhenwind.rocket.domain.client

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.rocket.core.infra.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.data.model.ClientProfile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientRepository @Inject constructor(
    private val clientLocalDataSource: ClientLocalDataSource,
    private val clientMapper: ClientMapper,
    private val clientProfileMapper: ClientProfileMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    fun observeClientProfileList(): Flow<PagingData<ClientProfile>> =
        clientLocalDataSource.observeClientProfileList()
            .map { pagingData ->
                pagingData.map {
                    clientProfileMapper.map(it)
                }
            }
            .flowOn(dispatcher)
}