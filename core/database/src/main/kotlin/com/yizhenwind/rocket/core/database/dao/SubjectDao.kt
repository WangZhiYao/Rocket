package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.SubjectEntity
import kotlinx.coroutines.flow.Flow

/**
 * 代练项目表操作
 *
 * @author WangZhiYao
 * @since 2021/10/29
 */
@Dao
interface SubjectDao : IDao<SubjectEntity> {

    @Query("SELECT * FROM subject WHERE enable = 1")
    fun observeSubject(): Flow<List<SubjectEntity>>

}