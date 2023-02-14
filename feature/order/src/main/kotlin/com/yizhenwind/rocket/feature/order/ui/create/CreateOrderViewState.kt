package com.yizhenwind.rocket.feature.order.ui.create

import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
import com.yizhenwind.rocket.core.model.simple.SimpleClient

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class CreateOrderViewState(
    val simpleClientList: List<SimpleClient> = emptyList(),
    val simpleClient: SimpleClient = SimpleClient(),
    val simpleAccountList: List<SimpleAccount> = emptyList(),
    val simpleAccount: SimpleAccount = SimpleAccount(),
    val simpleCharacterList: List<SimpleCharacter> = emptyList(),
    val simpleCharacter: SimpleCharacter = SimpleCharacter(),
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