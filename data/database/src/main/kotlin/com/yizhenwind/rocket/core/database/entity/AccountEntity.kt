package com.yizhenwind.rocket.core.database.entity

import androidx.room.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/7
 */
@Entity(
    tableName = "account",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("account", unique = true)
    ]
)
data class AccountEntity(

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
     * 账号
     */
    val account: String,

    /**
     * 密码
     */
    var password: String,

    /**
     * 备注
     */
    var remark: String? = null,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)