package com.yizhenwind.rocket.data.order.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yizhenwind.rocket.core.database.dao.OrderDao
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.dto.OrderProfileDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderLocalSource @Inject constructor(
    private val orderDao: OrderDao,
    @DatabasePagingConfig private val pagingConfig: PagingConfig
) {

    fun observeOrderProfileByClientId(clientId: Long): Flow<PagingData<OrderProfileDto>> =
        Pager(pagingConfig) {
            orderDao.observeOrderProfileByClientId(clientId)
        }
            .flow

}