package com.yizhenwind.rocket.core.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

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
     * 插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(item: T): Long

    /**
     * 批量插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(itemList: List<T>): List<Long>

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