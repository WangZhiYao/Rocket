package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 * 客户属性
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@Entity(
    tableName = "client",
    indices = [
        Index(value = ["contact_type", "contact"], unique = true)
    ]
)
data class ClientEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    /**
     * 客户名
     */
    val name: String,

    /**
     * 联系方式类型
     */
    @ColumnInfo(name = "contact_type")
    var contactType: ContactType,

    /**
     * 联系方式
     */
    var contact: String,

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