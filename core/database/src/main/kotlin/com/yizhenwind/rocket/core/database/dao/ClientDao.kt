package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.database.dto.simple.SimpleClientDto
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import kotlinx.coroutines.flow.Flow

/**
 * 客户表操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Dao
interface ClientDao : IDao<ClientEntity> {

    @Transaction
    @Query("SELECT * FROM client")
    fun observeClientList(): Flow<List<ClientDto>>

    /**
     * 分页查询客户简介
     */
    @Transaction
    @Query("SELECT id, name, contact_type_id, contact, ( SELECT COUNT(*) FROM account WHERE client_id = client.id ) AS account_count, ( SELECT COUNT(*) FROM character WHERE client_id = client.id ) AS character_count, ( SELECT COUNT(*) FROM `order` WHERE client_id = client.id ) AS order_count, create_time FROM client WHERE enable = 1 ORDER BY create_time DESC")
    fun observeClientProfile(): Flow<List<ClientProfileDto>>

    /**
     * 根据联系方式查询客户
     *
     * @param contactTypeId 联系方式类型
     * @param contact 联系方式
     */
    @Transaction
    @Query("SELECT * FROM client WHERE contact_type_id = :contactTypeId AND contact = :contact AND enable = 1 LIMIT 1")
    suspend fun getClientByContact(contactTypeId: Long, contact: String): ClientDto?

    /**
     * 根据ID查找客户
     *
     * @param id 客户ID
     */
    @Transaction
    @Query("SELECT * FROM client WHERE id = :id LIMIT 1")
    fun observeClientById(id: Long): Flow<ClientDto?>

    @Transaction
    @Query("SELECT id, name, contact_type_id, contact FROM client WHERE enable = 1")
    fun observeSimpleClientList(): Flow<List<SimpleClientDto>>

}