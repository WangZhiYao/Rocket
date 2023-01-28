package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@Entity(tableName = "zone")
data class ZoneEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    /**
     * 大区名
     */
    val name: String
)