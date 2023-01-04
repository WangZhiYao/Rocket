package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2022/1/14
 */
@Entity(tableName = "sect")
data class SectEntity(

    /**
     * 主键ID
     */
    @PrimaryKey
    val id: Long,

    /**
     * 门派名
     */
    val name: String,

    /**
     * 图标
     */
    val icon: String
)
