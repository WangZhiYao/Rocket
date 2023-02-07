package com.yizhenwind.rocket.core.model

import com.yizhenwind.rocket.core.common.constant.OrderStatus
import com.yizhenwind.rocket.core.common.constant.PaymentStatus

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class OrderProfile(
    val id: Long = 0,
    val subject: Subject = Subject(),
    val remark: String = "",
    val orderStatus: OrderStatus = OrderStatus.NOT_STARTED,
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val createTime: Long = System.currentTimeMillis()
)