package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 名称
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Entity(tableName = "billing_cycle")
data class BillingCycleEntity(

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
     * 是否为默认
     */
    val default: Boolean,

    /**
     * 是否可用
     */
    val enable: Boolean
)