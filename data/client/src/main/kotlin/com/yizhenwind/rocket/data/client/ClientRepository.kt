package com.yizhenwind.rocket.data.client

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.ClientMapper
import com.yizhenwind.rocket.core.database.mapper.ClientTupleMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientTuple
import com.yizhenwind.rocket.data.client.source.ClientLocalDataSource
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
    private val clientMapper: ClientMapper,
    private val clientTupleMapper: ClientTupleMapper
) {

    fun observeClientList(): Flow<List<Client>> =
        clientLocalDataSource.observeClientList()
            .map { clientDtoList ->
                EntityListMapper(clientMapper).fromEntity(clientDtoList)
            }

    fun createClient(client: Client): Flow<Client> =
        clientLocalDataSource.createClient(clientMapper.toEntity(client))
            .map { id -> client.copy(id = id) }

    suspend fun getClientByContact(contactType: ContactType, contact: String): Client? =
        clientLocalDataSource.getClientByContact(contactType, contact)
            ?.run { clientMapper.fromEntity(this) }

    fun observeClientById(id: Long): Flow<Client?> =
        clientLocalDataSource.observeClientById(id).map { clientDto ->
            clientDto?.run { clientMapper.fromEntity(this) }
        }

    fun observeClientTupleList(): Flow<List<ClientTuple>> =
        clientLocalDataSource.observeClientTupleList()
            .map { ListMapper(clientTupleMapper).map(it) }

    fun updateClient(client: Client): Flow<Int> =
        clientLocalDataSource.updateClient(clientMapper.toEntity(client))

    fun deleteClient(client: Client): Flow<Int> =
        clientLocalDataSource.deleteClient(clientMapper.toEntity(client))

}