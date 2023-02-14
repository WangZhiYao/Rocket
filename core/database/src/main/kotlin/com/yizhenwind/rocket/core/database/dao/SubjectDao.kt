package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.SubjectDto
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

    @Transaction
    @Query("SELECT * FROM subject WHERE enable = 1")
    fun observeSubjectList(): Flow<List<SubjectDto>>

    @Transaction
    @Query("SELECT * FROM subject WHERE category_id = :categoryId AND enable = 1")
    fun observeSubjectListByCategoryId(categoryId: Long): Flow<List<SubjectDto>>

}