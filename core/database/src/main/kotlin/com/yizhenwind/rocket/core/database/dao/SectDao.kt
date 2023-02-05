package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.SectInternalDto
import com.yizhenwind.rocket.core.database.entity.SectEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface SectDao : IDao<SectEntity> {

    @Transaction
    @Query("SELECT * FROM sect")
    fun observeSectInternal(): Flow<List<SectInternalDto>>

}