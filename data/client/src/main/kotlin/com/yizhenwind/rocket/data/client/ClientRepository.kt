package com.yizhenwind.rocket.data.client

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.ClientDtoMapper
import com.yizhenwind.rocket.core.database.mapper.ClientMapper
import com.yizhenwind.rocket.core.database.mapper.ClientProfileDtoMapper
import com.yizhenwind.rocket.core.database.mapper.ClientTupleMapper
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.core.model.ContactType
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
    private val clientProfileDtoMapper: ClientProfileDtoMapper,
    private val clientMapper: ClientMapper,
    private val clientDtoMapper: ClientDtoMapper,
    private val clientTupleMapper: ClientTupleMapper
) {

    fun observeClientList(): Flow<List<Client>> =
        clientLocalDataSource.observeClientList()
            .map { clientDtoList ->
                ListMapper(clientDtoMapper).map(clientDtoList)
            }

    fun observeClientProfileList(): Flow<List<ClientProfile>> =
        clientLocalDataSource.observeClientProfileList()
            .map { clientProfileDtoList ->
                ListMapper(clientProfileDtoMapper).map(clientProfileDtoList)
            }

    fun createClient(client: Client): Flow<Client> =
        clientLocalDataSource.createClient(clientMapper.map(client))
            .map { id -> client.copy(id = id) }

    suspend fun getClientByContact(contactType: ContactType, contact: String): Client? =
        clientLocalDataSource.getClientByContact(contactType.id, contact)
            ?.run { clientDtoMapper.map(this) }

    fun observeClientById(id: Long): Flow<Client> =
        clientLocalDataSource.observeClientById(id).map { clientDto ->
            clientDto?.run { clientDtoMapper.map(this) } ?: Client()
        }

    fun observeClientTupleList(): Flow<List<ClientTuple>> =
        clientLocalDataSource.observeClientTupleList()
            .map { ListMapper(clientTupleMapper).map(it) }

    fun updateClient(client: Client): Flow<Int> =
        clientLocalDataSource.updateClient(clientMapper.map(client))

    fun deleteClient(client: Client): Flow<Int> =
        clientLocalDataSource.deleteClient(clientMapper.map(client))

}