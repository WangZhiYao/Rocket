package com.yizhenwind.rocket.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.common.constant.ContactType
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
        "SELECT id, name, create_time, " +
                "(SELECT COUNT(*) FROM account WHERE client_id = client.id) AS account_count, " +
                "(SELECT COUNT(*) FROM character WHERE client_id = client.id) AS character_count, " +
                "(SELECT COUNT(*) FROM `order` WHERE client_id = client.id ) AS order_count " +
                "FROM client"
    )
    fun observeClientProfile(): PagingSource<Int, ClientProfileDto>

    /**
     * 根据联系方式查询客户
     */
    @Query("SELECT * FROM client WHERE contact_type = :contactType AND contact = :contact LIMIT 1")
    suspend fun getClientByContact(contactType: ContactType, contact: String): ClientEntity?

}