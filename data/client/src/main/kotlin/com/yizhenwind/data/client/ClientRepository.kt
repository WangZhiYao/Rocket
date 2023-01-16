package com.yizhenwind.data.client

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.data.client.mapper.ClientProfileDtoMapper
import com.yizhenwind.data.client.source.ClientLocalDataSource
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientRepository @Inject constructor(
    private val clientLocalDataSource: ClientLocalDataSource,
    private val clientProfileDtoMapper: ClientProfileDtoMapper
) {

    fun observeClientProfile(): Flow<PagingData<ClientProfile>> =
        clientLocalDataSource.observeClientProfile()
            .map { pagingData ->
                pagingData.map {
                    clientProfileDtoMapper.map(it)
                }
            }

}