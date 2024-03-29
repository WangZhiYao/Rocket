package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import com.yizhenwind.rocket.core.database.dto.CharacterTupleDto
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
    @Query("SELECT id, zone_id, server_id, name, sect_id, remark, create_time FROM character WHERE client_id = :clientId ORDER BY create_time DESC")
    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfileDto>>

    @Transaction
    @Query("SELECT id, zone_id, server_id, name, sect_id, remark, create_time FROM character WHERE account_id = :accountId ORDER BY create_time DESC")
    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfileDto>>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    fun observeCharacterById(id: Long): Flow<CharacterDto?>

    @Transaction
    @Query("SELECT id, account_id, zone_id, server_id, name, sect_id FROM character WHERE client_id = :clientId ORDER BY create_time DESC")
    fun observeCharacterTupleListByClientId(clientId: Long): Flow<List<CharacterTupleDto>>

    @Transaction
    @Query("SELECT id, account_id, zone_id, server_id, name, sect_id FROM character WHERE account_id = :accountId ORDER BY create_time DESC")
    fun observeCharacterTupleListByAccountId(accountId: Long): Flow<List<CharacterTupleDto>>

}