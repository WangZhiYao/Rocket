package com.yizhenwind.rocket.data.client

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.rocket.core.database.mapper.ClientDtoMapper
import com.yizhenwind.rocket.core.database.mapper.ClientMapper
import com.yizhenwind.rocket.core.database.mapper.ClientProfileDtoMapper
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientProfile
import com.yizhenwind.rocket.core.model.ContactType
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
) {

    fun observeClientProfile(): Flow<PagingData<ClientProfile>> =
        clientLocalDataSource.observeClientProfile()
            .map { pagingData ->
                pagingData.map {
                    clientProfileDtoMapper.map(it)
                }
            }

    fun createClient(client: Client): Flow<Client> =
        clientLocalDataSource.createClient(clientMapper.map(client))
            .map { id -> client.copy(id = id) }

    suspend fun getClientByContact(contactType: ContactType, contact: String): Client? =
        clientLocalDataSource.getClientByContact(contactType.id, contact)
            ?.run { clientDtoMapper.map(this) }

    suspend fun getClientById(id: Long): Client? =
        clientLocalDataSource.getClientById(id)?.run { clientDtoMapper.map(this) }

}