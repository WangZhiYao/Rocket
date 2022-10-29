package com.yizhenwind.rocket.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.CustomerEntity

/**
 * 客户表操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Dao
interface CustomerDao : IDao<CustomerEntity> {

    /**
     * 订阅查询所有客户
     */
    @Query("SELECT * FROM customer")
    fun observeCustomerList(): PagingSource<Int, CustomerEntity>

}