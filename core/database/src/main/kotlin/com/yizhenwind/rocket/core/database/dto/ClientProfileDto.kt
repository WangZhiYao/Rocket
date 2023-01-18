package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo

/**
 * 客户信息摘要
 *
 * @author WangZhiYao
 * @since 2022/10/30
 */
data class ClientProfileDto(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "account_count")
    val accountCount: Int,
    @ColumnInfo(name = "character_count")
    val characterCount: Int,
    @ColumnInfo(name = "order_count")
    val orderCount: Int,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)
