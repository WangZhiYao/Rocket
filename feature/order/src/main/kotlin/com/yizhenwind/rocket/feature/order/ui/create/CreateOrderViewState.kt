package com.yizhenwind.rocket.feature.order.ui.create

import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.*

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class CreateOrderViewState(
    val clientTupleList: List<ClientTuple> = emptyList(),
    val clientTuple: ClientTuple = ClientTuple(),
    val accountTupleList: List<AccountTuple> = emptyList(),
    val accountTuple: AccountTuple = AccountTuple(),
    val characterTupleList: List<CharacterTuple> = emptyList(),
    val characterTuple: CharacterTuple = CharacterTuple(),
    val categoryList: List<Category> = emptyList(),
    val category: Category = Category(),
    val subjectList: List<Subject> = emptyList(),
    val subject: Subject = Subject(),
    val startTime: Long = System.currentTimeMillis(),
    val totalAmount: Long = 0,
    val paymentStatusList: List<PaymentStatus> = emptyList(),
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val paymentMethodList: List<PaymentMethod> = emptyList(),
    val paymentMethod: PaymentMethod = PaymentMethod(),
    val paymentAmount: Long = 0,
    val paymentTime: Long = 0
) : IViewState