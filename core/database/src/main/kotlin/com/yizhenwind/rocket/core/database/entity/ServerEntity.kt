package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@Entity(
    tableName = "server",
    foreignKeys = [
        ForeignKey(
            entity = ZoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["zone_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ServerEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    /**
     * 大区ID
     */
    @ColumnInfo(name = "zone_id", index = true)
    val zoneId: Long,

    /**
     * 服务器名
     */
    val name: String
)