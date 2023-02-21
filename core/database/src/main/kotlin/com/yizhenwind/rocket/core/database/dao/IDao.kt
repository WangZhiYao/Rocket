package com.yizhenwind.rocket.core.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

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
    suspend fun insert(item: T): Long

    /**
     * 批量插入
     */
    @Insert
    suspend fun insert(itemList: List<T>): List<Long>

    /**
     * 更新或插入
     */
    @Upsert
    suspend fun upsert(item: T): Long

    /**
     * 批量插入或更新
     */
    @Upsert
    suspend fun upsert(itemList: List<T>): List<Long>

    /**
     * 更新
     */
    @Update
    suspend fun update(item: T): Int

    /**
     * 删除
     */
    @Delete
    suspend fun delete(item: T): Int

}