package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.ZoneServerDto
import com.yizhenwind.rocket.core.database.entity.ZoneEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface ZoneDao : IDao<ZoneEntity> {

    @Transaction
    @Query("SELECT * FROM zone")
    fun observeZoneServer(): Flow<List<ZoneServerDto>>

}