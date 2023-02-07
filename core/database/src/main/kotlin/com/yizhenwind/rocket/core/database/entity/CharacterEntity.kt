package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 账号属性
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Entity(tableName = "character")
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
     * 安全锁
     */
    @ColumnInfo(name = "security_lock")
    val securityLock: String,

    /**
     * 备注
     */
    val remark: String,

    /**
     * 是否可用
     */
    val enable: Boolean,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)