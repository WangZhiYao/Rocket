package com.yizhenwind.rocket.feature.order.ui.create

import android.app.Application
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.common.ext.toAmount
import com.yizhenwind.rocket.core.common.usecase.DataFlowSequenceUseCase
import com.yizhenwind.rocket.core.framework.base.BaseMVIAndroidViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.mediator.paymentmethod.IPaymentMethodService
import com.yizhenwind.rocket.core.mediator.subject.ISubjectService
import com.yizhenwind.rocket.core.model.*
import com.yizhenwind.rocket.domain.common.usecase.AccountTupleUseCase
import com.yizhenwind.rocket.domain.common.usecase.CharacterTupleUseCase
import com.yizhenwind.rocket.domain.common.usecase.ClientTupleUseCase
import com.yizhenwind.rocket.domain.common.usecase.TupleContext
import com.yizhenwind.rocket.domain.order.CreateOrderUseCase
import com.yizhenwind.rocket.feature.order.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.*
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    application: Application,
    private val clientTupleUseCase: ClientTupleUseCase,
    private val accountTupleUseCase: AccountTupleUseCase,
    private val characterTupleUseCase: CharacterTupleUseCase,
    private val categoryService: ICategoryService,
    private val subjectService: ISubjectService,
    private val paymentMethodService: IPaymentMethodService,
    private val createOrderUseCase: CreateOrderUseCase,
    private val logger: ILogger
) : BaseMVIAndroidViewModel<CreateOrderViewState, CreateOrderSideEffect>(application) {

    override val container =
        container<CreateOrderViewState, CreateOrderSideEffect>(CreateOrderViewState())

    val startDate: Calendar
        get() = Calendar.getInstance()
            .apply { timeInMillis = container.stateFlow.value.startTime }

    val paymentDate: Calendar
        get() = Calendar.getInstance()
            .apply { timeInMillis = container.stateFlow.value.paymentTime }

    init {
        intent {
            combine(
                categoryService.observeCategoryList(),
                subjectService.observeSubjectList(),
                flowOf(PaymentStatus.values()),
                paymentMethodService.observePaymentMethodList()
            ) { categoryList, subjectList, paymentStatusList, paymentMethodList ->
                state.copy(
                    categoryList = categoryList,
                    subjectList = subjectList,
                    paymentStatusList = paymentStatusList.toList(),
                    paymentMethodList = paymentMethodList
                )
            }
                .collect { viewState ->
                    reduce {
                        viewState
                    }
                }
        }
    }

    fun initViewState(clientId: Long, accountId: Long, characterId: Long) {
        intent {
            val sequenceUseCase = DataFlowSequenceUseCase<TupleContext>()
                .add(clientTupleUseCase)
                .add(accountTupleUseCase)
                .add(characterTupleUseCase)

            sequenceUseCase.execute(
                TupleContext(
                    clientTuple = ClientTuple(id = clientId),
                    accountTuple = AccountTuple(id = accountId),
                    characterTuple = CharacterTuple(id = characterId)
                )
            )
                .collect { tupleContext ->
                    tupleContext.apply {
                        reduce {
                            state.copy(
                                clientTupleList = clientTupleList,
                                clientTuple = clientTuple,
                                accountTupleList = accountTupleList,
                                accountTuple = accountTuple,
                                characterTupleList = characterTupleList,
                                characterTuple = characterTuple
                            )
                        }
                    }
                }
        }
    }

    fun onClientSelected(clientTuple: ClientTuple) {
        intent {
            if (clientTuple.id == state.clientTuple.id) {
                return@intent
            }

            val sequenceUseCase = DataFlowSequenceUseCase<TupleContext>()
                .add(accountTupleUseCase)
                .add(characterTupleUseCase)

            sequenceUseCase.execute(TupleContext(clientTuple = clientTuple))
                .collect { tupleContext ->
                    postSideEffect(CreateOrderSideEffect.HideClientError)
                    postSideEffect(CreateOrderSideEffect.HideAccountError)
                    postSideEffect(CreateOrderSideEffect.HideCharacterError)
                    tupleContext.apply {
                        reduce {
                            state.copy(
                                accountTupleList = accountTupleList,
                                accountTuple = accountTuple,
                                characterTupleList = characterTupleList,
                                characterTuple = characterTuple
                            )
                        }
                    }
                }
        }
    }

    fun onAccountSelected(accountTuple: AccountTuple) {
        intent {
            if (accountTuple.id == state.accountTuple.id) {
                return@intent
            }

            characterTupleUseCase.execute(
                TupleContext(
                    clientTuple = state.clientTuple,
                    accountTuple = accountTuple
                )
            )
                .collect { tupleContext ->
                    postSideEffect(CreateOrderSideEffect.HideAccountError)
                    postSideEffect(CreateOrderSideEffect.HideCharacterError)
                    tupleContext.apply {
                        reduce {
                            state.copy(
                                accountTuple = accountTuple,
                                characterTupleList = characterTupleList,
                                characterTuple = characterTuple
                            )
                        }
                    }
                }
        }
    }

    fun onCharacterSelected(characterTuple: CharacterTuple) {
        intent {
            postSideEffect(CreateOrderSideEffect.HideAccountError)
            postSideEffect(CreateOrderSideEffect.HideCharacterError)
            reduce {
                state.copy(
                    accountTuple = characterTuple.accountTuple,
                    characterTuple = characterTuple
                )
            }
        }
    }

    fun onCategorySelected(category: Category) {
        intent {
            if (category.id == state.category.id) {
                return@intent
            }
            subjectService.observeSubjectListByCategoryId(category.id)
                .collect { subjectList ->
                    val subject = if (subjectList.size == 1) subjectList[0] else Subject()
                    postSideEffect(CreateOrderSideEffect.HideCategoryError)
                    postSideEffect(CreateOrderSideEffect.HideSubjectError)
                    reduce {
                        state.copy(
                            category = category,
                            subjectList = subjectList,
                            subject = subject
                        )
                    }
                }
        }
    }

    fun onSubjectSelected(subject: Subject) {
        intent {
            postSideEffect(CreateOrderSideEffect.HideCategoryError)
            postSideEffect(CreateOrderSideEffect.HideSubjectError)
            reduce {
                state.copy(category = subject.category, subject = subject)
            }
        }
    }

    fun onDateSelected(isPayment: Boolean, timeInMillis: Long) {
        intent {
            val newDate = Calendar.getInstance().apply { this.timeInMillis = timeInMillis }
            val nowDate = Calendar.getInstance()
                .apply { this.timeInMillis = if (isPayment) state.paymentTime else state.startTime }
            nowDate.set(
                newDate.get(Calendar.YEAR),
                newDate.get(Calendar.MONTH),
                newDate.get(Calendar.DAY_OF_MONTH)
            )
            reduce {
                if (isPayment) {
                    state.copy(paymentTime = nowDate.timeInMillis)
                } else {
                    state.copy(startTime = nowDate.timeInMillis)
                }
            }
        }
    }

    fun onTimeSelected(isPayment: Boolean, hour: Int, minute: Int) {
        intent {
            val nowDate = Calendar.getInstance()
                .apply { this.timeInMillis = if (isPayment) state.paymentTime else state.startTime }
            nowDate.apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
            }
            reduce {
                if (isPayment) {
                    state.copy(paymentTime = nowDate.timeInMillis)
                } else {
                    state.copy(startTime = nowDate.timeInMillis)
                }
            }
        }
    }

    fun onTotalAmountChanged(totalAmountStr: String?) {
        intent {
            postSideEffect(CreateOrderSideEffect.HideTotalAmountError)
            val totalAmount = totalAmountStr.toAmount()
            state.apply {
                if (totalAmount > 0L && paymentStatus != PaymentStatus.UNPAID && totalAmount < paymentAmount) {
                    postSideEffect(CreateOrderSideEffect.ShowTotalAmountError(R.string.error_create_order_total_amount_can_not_lower_than_payment_amount))
                }
            }
            reduce {
                state.copy(totalAmount = totalAmount)
            }
        }
    }

    fun onPaymentStatusSelected(paymentStatus: PaymentStatus) {
        intent {
            reduce {
                when (paymentStatus) {
                    PaymentStatus.UNPAID ->
                        state.copy(
                            paymentStatus = paymentStatus,
                            paymentTime = 0,
                            paymentMethod = PaymentMethod(),
                            paymentAmount = 0
                        )
                    PaymentStatus.PARTIALLY_PAID, PaymentStatus.PAID -> {
                        state.copy(
                            paymentStatus = paymentStatus,
                            paymentTime = System.currentTimeMillis()
                        )
                    }
                }
            }
        }
    }

    fun onPaymentMethodSelected(paymentMethod: PaymentMethod) {
        intent {
            reduce {
                state.copy(paymentMethod = paymentMethod)
            }
        }
    }

    fun onPaymentAmountChanged(paymentAmountStr: String?) {
        intent {
            postSideEffect(CreateOrderSideEffect.HidePaymentAmountError)
            val paymentAmount = paymentAmountStr.toAmount()
            state.apply {
                if (totalAmount > 0L && paymentStatus != PaymentStatus.UNPAID && totalAmount < paymentAmount) {
                    postSideEffect(CreateOrderSideEffect.ShowPaymentAmountError(R.string.error_create_order_payment_amount_can_not_bigger_than_total_amount))
                }
            }
            reduce {
                state.copy(paymentAmount = paymentAmount)
            }
        }
    }

    fun attemptCreateOrder(remark: String?) {
        intent {
            state.apply {
                if (clientTuple.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowClientError(R.string.error_create_order_client))
                    return@intent
                }
                if (accountTuple.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowAccountError(R.string.error_create_order_account))
                    return@intent
                }
                if (characterTuple.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowCharacterError(R.string.error_create_order_character))
                    return@intent
                }
                if (category.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowCategoryError(R.string.error_create_order_category))
                    return@intent
                }
                if (subject.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowSubjectError(R.string.error_create_order_subject))
                    return@intent
                }
                if (totalAmount == 0L) {
                    postSideEffect(CreateOrderSideEffect.ShowTotalAmountError(R.string.error_create_order_total_amount))
                    return@intent
                }
                if (paymentStatus != PaymentStatus.UNPAID) {
                    if (paymentMethod.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreateOrderSideEffect.ShowPaymentMethodError(R.string.error_create_order_payment_method))
                        return@intent
                    }
                    if (paymentAmount == 0L) {
                        postSideEffect(CreateOrderSideEffect.ShowPaymentAmountError(R.string.error_create_order_payment_amount))
                        return@intent
                    }
                    if (paymentAmount > totalAmount) {
                        postSideEffect(CreateOrderSideEffect.ShowPaymentAmountError(R.string.error_create_order_payment_amount_can_not_bigger_than_total_amount))
                        return@intent
                    }
                }
                createOrderUseCase(
                    Order(
                        client = Client(clientTuple.id),
                        account = Account(accountTuple.id),
                        character = Character(characterTuple.id),
                        category = category,
                        subject = subject,
                        startTime = startTime,
                        totalAmount = totalAmount,
                        statusUpdateTime = System.currentTimeMillis(),
                        paymentStatus = if (paymentStatus == PaymentStatus.PARTIALLY_PAID && paymentAmount == totalAmount) PaymentStatus.PAID else paymentStatus,
                        paymentMethod = paymentMethod,
                        paymentTime = paymentTime,
                        paymentAmount = paymentAmount,
                        remark = remark.ifNull { "" }
                    )
                )
                    .catch {
                        logger.e(it)
                        emit(Order())
                    }
                    .collect { order ->
                        if (order.id == Constant.DEFAULT_ID) {
                            postSideEffect(CreateOrderSideEffect.CreateOrderFailed(R.string.error_create_order))
                        } else {
                            postSideEffect(CreateOrderSideEffect.CreateOrderSuccess(order))
                        }
                    }
            }
        }
    }

    fun reset() {
        intent {
            reduce {
                state.copy(
                    clientTuple = ClientTuple(),
                    accountTupleList = emptyList(),
                    accountTuple = AccountTuple(),
                    characterTupleList = emptyList(),
                    characterTuple = CharacterTuple(),
                    category = Category(),
                    subjectList = emptyList(),
                    subject = Subject(),
                    startTime = System.currentTimeMillis(),
                    totalAmount = 0,
                    paymentStatusList = emptyList(),
                    paymentStatus = PaymentStatus.UNPAID,
                    paymentMethodList = emptyList(),
                    paymentMethod = PaymentMethod(),
                    paymentAmount = 0,
                    paymentTime = 0
                )
            }
        }
    }
}