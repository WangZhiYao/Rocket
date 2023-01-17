package com.yizhenwind.data.client.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.database.dao.ClientDao
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
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
    private val clientDao: ClientDao,
    @DatabasePagingConfig private val pagingConfig: PagingConfig
) {

    fun observeClientProfile(): Flow<PagingData<ClientProfileDto>> =
        Pager(pagingConfig) {
            clientDao.observeClientProfile()
        }
            .flow

    fun createClient(clientEntity: ClientEntity): Flow<Long?> =
        flow {
            emit(clientDao.insert(clientEntity))
        }

    suspend fun getClientByContact(contactType: ContactType, contact: String): ClientEntity? =
        clientDao.getClientByContact(contactType, contact)

}