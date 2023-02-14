package com.yizhenwind.rocket.core.model

import com.yizhenwind.rocket.core.common.constant.OrderStatus
import com.yizhenwind.rocket.core.common.constant.PaymentStatus

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
data class Order(
    val id: Long = 0,
    val client: Client = Client(),
    val account: Account = Account(),
    val character: Character = Character(),
    val category: Category = Category(),
    val subject: Subject = Subject(),
    val startTime: Long = 0,
    val endTime: Long = 0,
    val totalAmount: Long = 0,
    val orderStatus: OrderStatus = OrderStatus.NOT_STARTED,
    val statusUpdateTime: Long = 0,
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val paymentMethod: PaymentMethod = PaymentMethod(),
    val paymentTime: Long = 0,
    val paymentAmount: Long = 0,
    val refundAmount: Long = 0,
    val refundTime: Long = 0,
    val remark: String = "",
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)