package com.yizhenwind.rocket.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.ClientDto
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
     * 分页查询客户简介
     */
    @Transaction
    @Query(
        "SELECT id, name,  create_time, " +
                "(SELECT COUNT(*) FROM character WHERE client_id = client.id) AS character_count, " +
                "(SELECT COUNT(*) FROM `order` WHERE client_id = client.id ) AS order_count " +
                "FROM client"
    )
    fun observeClientProfileList(): PagingSource<Int, ClientProfileDto>

    /**
     * 根据ID查询客户（包含联系方式）
     */
    @Transaction
    @Query("SELECT * FROM client WHERE id = :id")
    suspend fun getClientById(id: Long): ClientDto

}