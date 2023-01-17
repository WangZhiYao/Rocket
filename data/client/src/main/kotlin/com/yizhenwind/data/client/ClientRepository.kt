package com.yizhenwind.data.client

import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.data.client.mapper.ClientMapper
import com.yizhenwind.data.client.mapper.ClientProfileDtoMapper
import com.yizhenwind.data.client.source.ClientLocalDataSource
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.common.ext.ifNullOrElse
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
    private val logger: ILogger
) {

    fun observeClientProfile(): Flow<PagingData<ClientProfile>> =
        clientLocalDataSource.observeClientProfile()
            .map { pagingData ->
                pagingData.map {
                    clientProfileDtoMapper.map(it)
                }
            }

    fun createClient(client: Client): Flow<Client> =
        clientLocalDataSource.createClient(clientMapper.toEntity(client))
            .catch {
                logger.e(it)
                emit(null)
            }
            .map { id ->
                id.ifNullOrElse({ Client() }, { client.copy(id = it) })
            }

    suspend fun getClientByContact(contactType: ContactType, contact: String): Client? =
        clientLocalDataSource.getClientByContact(contactType, contact)
            ?.run { clientMapper.fromEntity(this) }

}