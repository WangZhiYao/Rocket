package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * 账号属性
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Entity(
    tableName = "character",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class CharacterEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 客户ID
     */
    @ColumnInfo(name = "client_id", index = true)
    val clientId: Long?,

    /**
     * 大区
     */
    @ColumnInfo(name = "zone_id")
    val zoneId: Long,

    /**
     * 服务器
     */
    @ColumnInfo(name = "server_id")
    val serverId: Long,

    /**
     * 账号ID
     */
    @ColumnInfo(name = "account_id", index = true)
    val accountId: Long,

    /**
     * 仓库锁
     */
    @ColumnInfo(name = "security_lock")
    val securityLock: String? = null,

    /**
     * 角色名
     */
    val name: String,

    /**
     * 门派
     */
    @ColumnInfo(name = "sect_id")
    val sectId: Long,

    /**
     * 心法
     */
    @ColumnInfo(name = "internal_id")
    val internalId: Long,

    /**
     * 备注
     */
    val remark: String? = null,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)