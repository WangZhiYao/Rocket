package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.yizhenwind.rocket.core.common.constant.BillingCycle
import com.yizhenwind.rocket.core.common.constant.OrderStatus
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
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.NO_ACTION,
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
     * 代练角色
     */
    @ColumnInfo(name = "character_id", index = true)
    val characterId: Long?,

    /**
     * 代练项目
     */
    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: Long,

    /**
     * 收费模式
     */
    @ColumnInfo(name = "billing_cycle")
    val billingCycle: BillingCycle,

    /**
     * 原价（分）
     */
    @ColumnInfo(name = "original_price")
    val originalPrice: Long,

    /**
     * 开始时间
     */
    @ColumnInfo(name = "start_date")
    val startDate: Long,

    /**
     * 结束时间
     */
    @ColumnInfo(name = "end_date")
    val endDate: Long? = null,

    /**
     * 备注
     */
    val remark: String? = null,

    /**
     * 订单状态
     */
    val status: OrderStatus,

    /**
     * 订单状态更新时间
     */
    @ColumnInfo(name = "status_update_time")
    val statusUpdateTime: Long,

    /**
     * 付款状态
     */
    @ColumnInfo(name = "payment_status")
    val paymentStatus: PaymentStatus,

    /**
     * 付款时间
     */
    @ColumnInfo(name = "payment_time")
    val paymentTime: Long? = null,

    /**
     * 退款金额
     */
    @ColumnInfo(name = "refund_amount")
    val refundAmount: Long? = null,

    /**
     * 退款时间
     */
    @ColumnInfo(name = "refund_time")
    val refundTime: Long? = null,

    /**
     * 更新时间
     */
    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)
