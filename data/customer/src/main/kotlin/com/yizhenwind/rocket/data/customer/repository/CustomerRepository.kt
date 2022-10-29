package com.yizhenwind.rocket.data.customer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.rocket.core.database.dao.CustomerDao
import com.yizhenwind.rocket.data.customer.mapper.CustomerEntityToCustomerMapper
import com.yizhenwind.rocket.data.customer.model.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/20
 */
class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao,
    private val customerEntityToCustomerMapper: CustomerEntityToCustomerMapper,
) {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    fun observeCustomerList(): Flow<PagingData<Customer>> =
        Pager(pagingConfig) {
            customerDao.observeCustomerList()
        }.flow
            .map { pagingData ->
                pagingData.map { customerEntity ->
                    customerEntityToCustomerMapper.map(customerEntity)
                }
            }

}