package com.yizhenwind.rocket.core.database.dao

import androidx.room.*

/**
 * 数据库操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
interface IDao<T> {

    /**
     * 插入
     */
    @Insert
    suspend fun insert(item: T): Long?

    /**
     * 批量插入
     */
    @Insert
    suspend fun insert(vararg item: T): List<Long>

    /**
     * 更新或插入
     */
    @Upsert
    suspend fun upsert(item: T): Long

    /**
     * 更新
     */
    @Update
    suspend fun update(item: T)

    /**
     * 删除
     */
    @Delete
    suspend fun delete(item: T): Int
}