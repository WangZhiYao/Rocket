package com.yizhenwind.rocket.domain.client.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.database.dao.ClientDao
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientLocalDataSource @Inject constructor(
    private val clientDao: ClientDao,
    @DatabasePagingConfig private val pagingConfig: PagingConfig
) {

    fun observeClientProfileList(): Flow<PagingData<ClientProfileDto>> =
        Pager(pagingConfig) {
            clientDao.observeClientProfileList()
        }.flow

    fun createClient(name: String, remark: String?): Flow<Long?> =
        flow {
            emit(clientDao.insert(ClientEntity(name = name, remark = remark)))
        }

    fun getClientById(id: Long): Flow<ClientDto?> =
        flow {
            emit(clientDao.getClientById(id))
        }

    fun deleteClient(client: ClientEntity): Flow<Int> =
        flow {
            emit(clientDao.delete(client))
        }
}