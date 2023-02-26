package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.yizhenwind.rocket.core.common.constant.OrderStatus
import com.yizhenwind.rocket.core.common.constant.PaymentMethod
import com.yizhenwind.rocket.core.common.constant.PaymentStatus

/**
 * 订单属性
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Entity(
    tableName = "order",
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
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class OrderEntity(

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
     * 角色ID
     */
    @ColumnInfo(name = "character_id", index = true)
    val characterId: Long,

    /**
     * 项目ID
     */
    @ColumnInfo(name = "category_id", index = true)
    val categoryId: Long,

    /**
     * 项目ID
     */
    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: Long,

    /**
     * 开始时间
     */
    @ColumnInfo(name = "start_time")
    val startTime: Long,

    /**
     * 结束时间
     */
    @ColumnInfo(name = "end_time")
    val endTime: Long,

    /**
     * 总价（分）
     */
    @ColumnInfo(name = "total_amount")
    val totalAmount: Long,

    /**
     * 订单状态
     */
    @ColumnInfo(name = "order_status")
    val orderStatus: OrderStatus,

    /**
     * 订单状态更新时间
     */
    @ColumnInfo(name = "status_update_time")
    val statusUpdateTime: Long,

    /**
     * 支付状态
     */
    @ColumnInfo(name = "payment_status")
    val paymentStatus: PaymentStatus,

    /**
     * 支付方式
     */
    @ColumnInfo(name = "payment_method")
    val paymentMethod: PaymentMethod,

    /**
     * 支付时间
     */
    @ColumnInfo(name = "payment_time")
    val paymentTime: Long,

    /**
     * 支付金额（分）
     */
    @ColumnInfo(name = "payment_amount")
    val paymentAmount: Long,

    /**
     * 退款金额（分）
     */
    @ColumnInfo(name = "refund_amount")
    val refundAmount: Long,

    /**
     * 退款时间
     */
    @ColumnInfo(name = "refund_time")
    val refundTime: Long,

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
