package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.BillingCycleEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface BillingCycleDao : IDao<BillingCycleEntity> {

    @Query("SELECT * FROM billing_cycle WHERE enable = 1")
    fun observeBillingCycle(): Flow<List<BillingCycleEntity>>

}