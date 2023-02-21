package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.database.dto.ClientTupleDto
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
     * 根据用户订单状态更新时间排序
     */
    @Transaction
    @Query("SELECT id, name, contact_type_id, contact, ( SELECT COUNT(*) FROM account WHERE client_id = client.id ) AS account_count , ( SELECT COUNT(*) FROM character WHERE client_id = client.id ) AS character_count, ( SELECT COUNT(*) FROM `order` WHERE client_id = client.id AND `order`.enable = 1 ) AS order_count, remark, create_time FROM client ORDER BY ( SELECT create_time FROM `order` WHERE `order`.client_id = client_id AND enable = 1 ) DESC")
    fun observeClientProfileList(): Flow<List<ClientProfileDto>>

    /**
     * 根据联系方式查询客户
     *
     * @param contactTypeId 联系方式类型
     * @param contact 联系方式
     */
    @Transaction
    @Query("SELECT * FROM client WHERE contact_type_id = :contactTypeId AND contact = :contact LIMIT 1")
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
    @Query("SELECT id, name, contact_type_id, contact FROM client")
    fun observeClientTupleList(): Flow<List<ClientTupleDto>>

}