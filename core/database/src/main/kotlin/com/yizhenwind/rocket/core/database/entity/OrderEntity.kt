package com.yizhenwind.rocket.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * 订单属性
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Entity(tableName = "order")
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
     * 代练角色
     */
    @ColumnInfo(name = "character_id", index = true)
    val characterId: Long,

    /**
     * 代练项目
     */
    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: Long,

    /**
     * 收费模式
     */
    @ColumnInfo(name = "billing_cycle_id")
    val billingCycleId: Long,

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
    val endDate: Long?,

    /**
     * 备注
     */
    val remark: String?,

    /**
     * 订单状态
     */
    @ColumnInfo(name = "order_status_id")
    val orderStatusId: Long,

    /**
     * 订单状态更新时间
     */
    @ColumnInfo(name = "status_update_time")
    val statusUpdateTime: Long,

    /**
     * 付款状态
     */
    @ColumnInfo(name = "payment_status_id")
    val paymentStatusId: Long,

    /**
     * 付款方式ID
     */
    @ColumnInfo(name = "payment_method_id")
    val paymentMethodId: Long,

    /**
     * 付款时间
     */
    @ColumnInfo(name = "payment_time")
    val paymentTime: Long?,

    /**
     * 退款金额
     */
    @ColumnInfo(name = "refund_amount")
    val refundAmount: Long?,

    /**
     * 退款时间
     */
    @ColumnInfo(name = "refund_time")
    val refundTime: Long?,

    /**
     * 是否可用
     */
    val enable: Boolean,

    /**
     * 更新时间
     */
    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)
