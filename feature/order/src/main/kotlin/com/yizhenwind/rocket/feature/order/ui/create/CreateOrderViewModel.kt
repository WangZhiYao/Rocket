package com.yizhenwind.rocket.feature.order.ui.create

import android.app.Application
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.common.ext.ifNull
import com.yizhenwind.rocket.core.framework.base.BaseMVIAndroidViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.mediator.paymentmethod.IPaymentMethodService
import com.yizhenwind.rocket.core.mediator.subject.ISubjectService
import com.yizhenwind.rocket.core.model.*
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
import com.yizhenwind.rocket.core.model.simple.SimpleClient
import com.yizhenwind.rocket.domain.order.CreateOrderUseCase
import com.yizhenwind.rocket.feature.order.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigDecimal
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
    private val clientService: IClientService,
    private val accountService: IAccountService,
    private val characterService: ICharacterService,
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

    @OptIn(FlowPreview::class)
    fun initViewState(clientId: Long, accountId: Long) {
        intent {
            clientService.observeSimpleClientList()
                .map { simpleClientList ->
                    var simpleClient = if (clientId == Constant.DEFAULT_ID) {
                        null
                    } else {
                        simpleClientList.find { it.id == clientId }
                    }

                    if (simpleClient == null && simpleClientList.size == 1) {
                        simpleClient = simpleClientList[0]
                    }

                    state.copy(
                        simpleClientList = simpleClientList,
                        simpleClient = simpleClient.ifNull { SimpleClient() }
                    )
                }
                .flatMapConcat { viewState ->
                    viewState.simpleClient.run {
                        if (id == Constant.DEFAULT_ID) {
                            flowOf(
                                viewState.copy(
                                    simpleAccountList = emptyList(),
                                    simpleAccount = SimpleAccount()
                                )
                            )
                        } else {
                            accountService.observeSimpleAccountListByClientId(id)
                                .map { simpleAccountList ->
                                    var simpleAccount = if (accountId == Constant.DEFAULT_ID) {
                                        null
                                    } else {
                                        simpleAccountList.find { it.id == accountId }
                                    }

                                    if (simpleAccount == null && simpleAccountList.size == 1) {
                                        simpleAccount = simpleAccountList[0]
                                    }

                                    viewState.copy(
                                        simpleAccountList = simpleAccountList,
                                        simpleAccount = simpleAccount.ifNull { SimpleAccount() }
                                    )
                                }
                        }
                    }
                }
                .flatMapConcat { viewState ->
                    viewState.run {
                        if (simpleClient.id == Constant.DEFAULT_ID) {
                            flowOf(
                                viewState.copy(
                                    simpleCharacterList = emptyList(),
                                    simpleCharacter = SimpleCharacter()
                                )
                            )
                        } else if (simpleAccount.id != Constant.DEFAULT_ID) {
                            characterService.observeSimpleCharacterListByAccountId(simpleAccount.id)
                                .map { simpleCharacterList ->
                                    val simpleCharacter = if (simpleCharacterList.size == 1) {
                                        simpleCharacterList[0]
                                    } else {
                                        SimpleCharacter()
                                    }

                                    viewState.copy(
                                        simpleCharacterList = simpleCharacterList,
                                        simpleCharacter = simpleCharacter
                                    )
                                }
                        } else {
                            characterService.observeSimpleCharacterListByClientId(simpleClient.id)
                                .map { simpleCharacterList ->
                                    viewState.copy(
                                        simpleCharacterList = simpleCharacterList,
                                        simpleCharacter = SimpleCharacter()
                                    )
                                }
                        }
                    }
                }
                .collect { viewState ->
                    reduce {
                        viewState
                    }
                }
        }
    }

    @OptIn(FlowPreview::class)
    fun onClientSelected(simpleClient: SimpleClient) {
        intent {
            if (simpleClient.id == state.simpleClient.id) {
                return@intent
            }
            accountService.observeSimpleAccountListByClientId(simpleClient.id)
                .map { simpleAccountList ->
                    val simpleAccount = if (simpleAccountList.size == 1) {
                        simpleAccountList[0]
                    } else {
                        SimpleAccount()
                    }

                    state.copy(
                        simpleAccountList = simpleAccountList,
                        simpleAccount = simpleAccount
                    )
                }
                .flatMapConcat { viewState ->
                    viewState.simpleAccount.run {
                        if (id == Constant.DEFAULT_ID) {
                            characterService.observeSimpleCharacterListByClientId(simpleClient.id)
                                .map { simpleCharacterList ->
                                    viewState.copy(
                                        simpleCharacterList = simpleCharacterList,
                                        simpleCharacter = SimpleCharacter()
                                    )
                                }
                        } else {
                            characterService.observeSimpleCharacterListByAccountId(id)
                                .map { simpleCharacterList ->
                                    val simpleCharacter = if (simpleCharacterList.size == 1) {
                                        simpleCharacterList[0]
                                    } else {
                                        SimpleCharacter()
                                    }

                                    viewState.copy(
                                        simpleCharacterList = simpleCharacterList,
                                        simpleCharacter = simpleCharacter
                                    )
                                }
                        }
                    }
                }
                .collect { viewState ->
                    postSideEffect(CreateOrderSideEffect.HideClientError)
                    postSideEffect(CreateOrderSideEffect.HideAccountError)
                    postSideEffect(CreateOrderSideEffect.HideCharacterError)
                    reduce {
                        viewState
                    }
                }
        }
    }

    fun onAccountSelected(simpleAccount: SimpleAccount) {
        intent {
            if (simpleAccount.id == state.simpleAccount.id) {
                return@intent
            }
            characterService.observeSimpleCharacterListByAccountId(simpleAccount.id)
                .collect { simpleCharacterList ->
                    val simpleCharacter = if (simpleCharacterList.size == 1) {
                        simpleCharacterList[0]
                    } else {
                        SimpleCharacter()
                    }
                    postSideEffect(CreateOrderSideEffect.HideAccountError)
                    postSideEffect(CreateOrderSideEffect.HideCharacterError)
                    reduce {
                        state.copy(
                            simpleAccount = simpleAccount,
                            simpleCharacterList = simpleCharacterList,
                            simpleCharacter = simpleCharacter
                        )
                    }
                }
        }
    }

    fun onCharacterSelected(simpleCharacter: SimpleCharacter) {
        intent {
            postSideEffect(CreateOrderSideEffect.HideAccountError)
            postSideEffect(CreateOrderSideEffect.HideCharacterError)
            reduce {
                state.copy(
                    simpleAccount = simpleCharacter.simpleAccount,
                    simpleCharacter = simpleCharacter
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
            val totalAmount = if (totalAmountStr.isNullOrBlank()) {
                0
            } else {
                BigDecimal(totalAmountStr).multiply(BigDecimal(100)).longValueExact()
            }
            reduce {
                if (totalAmount != 0L
                    && state.paymentStatus == PaymentStatus.PARTIALLY_PAID
                    && totalAmount == state.paymentAmount
                ) {
                    state.copy(totalAmount = totalAmount, paymentStatus = PaymentStatus.PAID)
                } else {
                    state.copy(totalAmount = totalAmount)
                }
            }
        }
    }

    fun onPaymentStatusSelected(paymentStatus: PaymentStatus) {
        intent {
            reduce {
                if (paymentStatus != PaymentStatus.UNPAID) {
                    val paymentTime = if (state.paymentTime == 0L) {
                        System.currentTimeMillis()
                    } else {
                        state.paymentTime
                    }
                    state.copy(paymentStatus = paymentStatus, paymentTime = paymentTime)
                } else {
                    state.copy(paymentStatus = paymentStatus, paymentTime = 0)
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
            val paymentAmount = if (paymentAmountStr.isNullOrBlank()) {
                0
            } else {
                BigDecimal(paymentAmountStr).multiply(BigDecimal(100)).longValueExact()
            }
            reduce {
                if (state.totalAmount != 0L
                    && state.paymentStatus == PaymentStatus.PARTIALLY_PAID
                    && state.totalAmount == paymentAmount
                ) {
                    state.copy(paymentStatus = PaymentStatus.PAID, paymentAmount = paymentAmount)
                } else {
                    state.copy(paymentAmount = paymentAmount)
                }
            }
        }
    }

    fun attemptCreateOrder(remark: String?) {
        intent {
            state.apply {
                if (simpleClient.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowClientError(R.string.error_create_order_client))
                    return@intent
                }
                if (simpleAccount.id == Constant.DEFAULT_ID) {
                    postSideEffect(CreateOrderSideEffect.ShowAccountError(R.string.error_create_order_account))
                    return@intent
                }
                if (simpleCharacter.id == Constant.DEFAULT_ID) {
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
                }
                createOrderUseCase(
                    Order(
                        client = Client(simpleClient.id),
                        account = Account(simpleAccount.id),
                        character = Character(simpleCharacter.id),
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
}