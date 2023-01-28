package com.yizhenwind.rocket.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@Entity(tableName = "sect")
data class SectEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    /**
     * 门派名
     */
    val name: String
)