package com.yizhenwind.rocket.domain.client.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.common.ext.ifNullOrElse
import com.yizhenwind.rocket.core.database.mapper.ClientDtoMapper
import com.yizhenwind.rocket.core.database.mapper.ClientMapper
import com.yizhenwind.rocket.core.database.mapper.ClientProfileMapper
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.domain.client.source.ClientLocalDataSource
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
    private val clientDtoMapper: ClientDtoMapper,
    private val clientProfileMapper: ClientProfileMapper,
    private val clientMapper: ClientMapper,
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

    fun createClient(name: String, remark: String?): Flow<Client> =
        clientLocalDataSource.createClient(name, remark)
            .map { id ->
                id.ifNullOrElse({
                    Client()
                }, {
                    Client(id = it, name = name, remark = remark)
                })
            }
            .flowOn(dispatcher)

    fun getClientById(id: Long): Flow<Client> =
        clientLocalDataSource.getClientById(id)
            .map { clientDto ->
                clientDto.ifNullOrElse({ Client() }, { clientDtoMapper.map(it) })
            }
            .flowOn(dispatcher)

    fun deleteClient(client: Client): Flow<Boolean> =
        clientLocalDataSource.deleteClient(clientMapper.map(client))
            .map {
                it > 0
            }
            .flowOn(dispatcher)

}