package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.PeriodEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface PeriodDao : IDao<PeriodEntity> {

    @Query("SELECT * FROM period WHERE enable = 1")
    fun observePeriod(): Flow<List<PeriodEntity>>

}