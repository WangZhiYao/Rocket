package com.yizhenwind.rocket.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.database.entity.ClientEntity

/**
 * 客户表操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Dao
interface ClientDao : IDao<ClientEntity> {

    /**
     * 分页查询所有客户
     */
    @Query("SELECT * FROM client")
    fun observeClientList(): PagingSource<Int, ClientEntity>

    /**
     * 分页查询客户简介
     */
    @Query(
        "SELECT id, name, contact_type, contact, create_time, " +
                "(SELECT COUNT(*) FROM account WHERE client_id = client.id) AS account_count, " +
                "(SELECT COUNT(*) FROM character WHERE client_id = client.id) AS character_count, " +
                "(SELECT COUNT(*) FROM `order` WHERE client_id = client.id ) AS order_count " +
                "FROM client"
    )
    fun observeClientProfileList(): PagingSource<Int, ClientProfileDto>

}