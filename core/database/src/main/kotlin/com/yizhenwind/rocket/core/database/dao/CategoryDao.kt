package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/5
 */
@Dao
interface CategoryDao : IDao<CategoryEntity> {

    @Query("SELECT * FROM category WHERE enable = 1")
    fun observeCategory(): Flow<List<CategoryEntity>>

}