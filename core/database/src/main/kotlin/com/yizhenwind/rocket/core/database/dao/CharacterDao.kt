package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import com.yizhenwind.rocket.core.database.dto.simple.SimpleCharacterDto
import com.yizhenwind.rocket.core.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

/**
 * 角色表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface CharacterDao : IDao<CharacterEntity> {

    @Transaction
    @Query("SELECT id, zone_id, server_id, name, sect_id, remark, create_time FROM character WHERE client_id = :clientId AND enable = 1 ORDER BY create_time DESC")
    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfileDto>>

    @Transaction
    @Query("SELECT id, zone_id, server_id, name, sect_id, remark, create_time FROM character WHERE account_id = :accountId AND enable = 1 ORDER BY create_time DESC")
    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfileDto>>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    fun observeCharacterById(id: Long): Flow<CharacterDto?>

    @Transaction
    @Query("SELECT id, account_id, zone_id, server_id, name, sect_id FROM character WHERE client_id = :clientId AND enable = 1 ORDER BY create_time DESC")
    fun observeSimpleCharacterListByClientId(clientId: Long): Flow<List<SimpleCharacterDto>>

    @Transaction
    @Query("SELECT id, account_id, zone_id, server_id, name, sect_id FROM character WHERE account_id = :accountId AND enable = 1 ORDER BY create_time DESC")
    fun observeSimpleCharacterListByAccountId(accountId: Long): Flow<List<SimpleCharacterDto>>

}