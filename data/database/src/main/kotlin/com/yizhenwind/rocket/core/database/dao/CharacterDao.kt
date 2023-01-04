package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.CharacterDto
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
    @Query("SELECT * FROM character WHERE client_id = :clientId")
    fun observeCharacterListByClientId(clientId: Long): Flow<List<CharacterDto>>

}