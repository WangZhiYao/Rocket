package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 名称
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Entity(tableName = "period")
data class PeriodEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    /**
     * 名称
     */
    val value: String,

    /**
     * 是否为默认
     */
    val default: Boolean,

    /**
     * 是否可用
     */
    val enable: Boolean
)