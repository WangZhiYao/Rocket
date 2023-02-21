package com.yizhenwind.rocket.core.database.entity

import androidx.room.*

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
        ),
    ],
    indices = [Index(value = ["server_id", "name"], unique = true)]
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
    val clientId: Long,

    /**
     * 账号ID
     */
    @ColumnInfo(name = "account_id", index = true)
    val accountId: Long,

    /**
     * 大区
     */
    @ColumnInfo(name = "zone_id", index = true)
    val zoneId: Long,

    /**
     * 服务器
     */
    @ColumnInfo(name = "server_id", index = true)
    val serverId: Long,

    /**
     * 角色名
     */
    val name: String,

    /**
     * 门派
     */
    @ColumnInfo(name = "sect_id", index = true)
    val sectId: Long,

    /**
     * 心法
     */
    @ColumnInfo(name = "internal_id", index = true)
    val internalId: Long,

    /**
     * 安全锁
     */
    @ColumnInfo(name = "security_lock")
    val securityLock: String,

    /**
     * 备注
     */
    val remark: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)