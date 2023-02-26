package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.common.constant.ContactType
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

    /**
     * 订阅查询客户信息
     */
    @Query("SELECT * FROM client ORDER BY create_time DESC")
    fun observeClientList(): Flow<List<ClientEntity>>

    /**
     * 根据联系方式查询客户
     *
     * @param contactType 联系方式类型
     * @param contact 联系方式
     */
    @Query("SELECT * FROM client WHERE contact_type = :contactType AND contact = :contact LIMIT 1")
    suspend fun getClientByContact(contactType: ContactType, contact: String): ClientEntity?

    /**
     * 根据ID查找客户
     *
     * @param id 客户ID
     */
    @Query("SELECT * FROM client WHERE id = :id LIMIT 1")
    fun observeClientById(id: Long): Flow<ClientEntity?>

    /**
     * 订阅查询客户基础信息
     */
    @Query("SELECT id, name, contact_type, contact FROM client")
    fun observeClientTupleList(): Flow<List<ClientTupleDto>>

}