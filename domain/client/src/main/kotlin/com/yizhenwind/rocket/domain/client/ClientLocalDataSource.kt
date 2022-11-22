package com.yizhenwind.rocket.domain.client

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.database.dao.ClientDao
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import kotlinx.coroutines.flow.Flow
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

}