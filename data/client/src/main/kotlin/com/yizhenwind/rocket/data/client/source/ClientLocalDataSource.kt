package com.yizhenwind.rocket.data.client.source

import com.yizhenwind.rocket.core.database.dao.ClientDao
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.database.dto.ClientTupleDto
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
class ClientLocalDataSource @Inject constructor(
    private val clientDao: ClientDao
) {

    fun observeClientList(): Flow<List<ClientDto>> =
        clientDao.observeClientList()

    fun observeClientProfileList(): Flow<List<ClientProfileDto>> =
        clientDao.observeClientProfileList()

    fun createClient(clientEntity: ClientEntity): Flow<Long> =
        flow {
            emit(clientDao.insert(clientEntity))
        }

    suspend fun getClientByContact(contactTypeId: Long, contact: String): ClientDto? =
        clientDao.getClientByContact(contactTypeId, contact)

    fun observeClientById(id: Long): Flow<ClientDto?> =
        clientDao.observeClientById(id)

    fun observeClientTupleList(): Flow<List<ClientTupleDto>> =
        clientDao.observeClientTupleList()

    fun updateClient(clientEntity: ClientEntity): Flow<Int> =
        flow {
            emit(clientDao.update(clientEntity))
        }

    fun deleteClient(clientEntity: ClientEntity): Flow<Int> =
        flow {
            emit(clientDao.delete(clientEntity))
        }

}