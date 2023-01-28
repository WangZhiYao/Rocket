package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
data class AccountProfileDto(
    val id: Long,
    val username: String,
    @ColumnInfo(name = "character_count")
    val characterCount: Int,
    @ColumnInfo(name = "order_count")
    val orderCount: Int,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)