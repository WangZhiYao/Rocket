package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Entity(tableName = "order_status")
data class OrderStatusEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    /**
     * 名称
     */
    val name: String,

    /**
     * 是否为默认的
     */
    val default: Boolean,

    /**
     * 是否可用
     */
    val enable: Boolean
)