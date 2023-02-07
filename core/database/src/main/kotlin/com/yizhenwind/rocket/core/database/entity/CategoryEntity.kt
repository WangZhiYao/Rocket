package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 分类表
 *
 * @author WangZhiYao
 * @since 2022/1/24
 */
@Entity(tableName = "category")
data class CategoryEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 标题
     */
    val title: String,

    /**
     * 描述
     */
    val description: String,

    /**
     * 是否为默认
     */
    val default: Boolean,

    /**
     * 是否可用
     */
    val enable: Boolean,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)