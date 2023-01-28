package com.yizhenwind.rocket.core.model

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class OrderProfile(
    val id: Long = 0,
    val subject: Subject = Subject(),
    val remark: String? = null,
    val orderStatus: OrderStatus = OrderStatus(),
    val paymentStatus: PaymentStatus = PaymentStatus(),
    val createTime: Long = System.currentTimeMillis()
)