package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 代练项目
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Entity(tableName = "subject")
data class SubjectEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 代练类型
     */
    @ColumnInfo(name = "category_id", index = true)
    val categoryId: Long,

    /**
     * 代练内容
     */
    val content: String,

    /**
     * 是否为默认的
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