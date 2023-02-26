package com.yizhenwind.rocket.feature.order.ui.create

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.common.ext.formatDate
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.AccountTupleDropDownAdapter
import com.yizhenwind.rocket.core.framework.widget.CategoryDropDownAdapter
import com.yizhenwind.rocket.core.framework.widget.CharacterTupleDropDownAdapter
import com.yizhenwind.rocket.core.framework.widget.ClientTupleDropDownAdapter
import com.yizhenwind.rocket.feature.order.R
import com.yizhenwind.rocket.feature.order.databinding.FragmentCreateOrderBinding
import com.yizhenwind.rocket.feature.order.ui.widget.*
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import java.util.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
@AndroidEntryPoint
class CreateOrderFragment :
    BaseFragment<FragmentCreateOrderBinding>(FragmentCreateOrderBinding::inflate),
    IMVIHost<CreateOrderViewState, CreateOrderSideEffect> {

    private val viewModel by viewModels<CreateOrderViewModel>()
    private val navArgs by navArgs<CreateOrderFragmentArgs>()

    private val clientAdapter = ClientTupleDropDownAdapter()

    private val accountAdapter = AccountTupleDropDownAdapter()

    private val characterAdapter = CharacterTupleDropDownAdapter()

    private val categoryAdapter by lazy { CategoryDropDownAdapter(requireContext()) }

    private val subjectAdapter = SubjectDropDownAdapter()

    private val paymentStatusAdapter by lazy { PaymentStatusDropDownAdapter(requireContext()) }

    private val paymentMethodAdapter by lazy { PaymentMethodDropDownAdapter(requireContext()) }

    private val decimalDigitsInputFilter = DecimalDigitsInputFilter()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            initViewState(navArgs.clientId, navArgs.accountId, navArgs.characterId)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        binding.apply {
            actvCreateOrderClient.apply {
                setAdapter(clientAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onClientSelected(clientAdapter.getItem(position))
                }
            }
            actvCreateOrderAccount.apply {
                setAdapter(accountAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onAccountSelected(accountAdapter.getItem(position))
                }
            }
            actvCreateOrderCharacter.apply {
                setAdapter(characterAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onCharacterSelected(characterAdapter.getItem(position))
                }
            }
            actvCreateOrderCategory.apply {
                setAdapter(categoryAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onCategorySelected(categoryAdapter.getItem(position))
                }
            }
            actvCreateOrderSubject.apply {
                setAdapter(subjectAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onSubjectSelected(subjectAdapter.getItem(position))
                }
            }
            tietCreateOrderStartDate.apply {
                inputType = InputType.TYPE_NULL
                setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        requestFocus()
                        showDatePickerDialog(false)
                    }
                    return@setOnTouchListener false
                }
            }
            tietCreateOrderStartTime.apply {
                inputType = InputType.TYPE_NULL
                setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        requestFocus()
                        showTimePickerDialog(false)
                    }
                    return@setOnTouchListener false
                }
            }
            tietCreateOrderTotalAmount.apply {
                filters = arrayOf(decimalDigitsInputFilter)
                doAfterTextChanged { totalAmountStr ->
                    viewModel.onTotalAmountChanged(totalAmountStr?.toString())
                }
            }
            actvCreateOrderPaymentStatus.apply {
                setAdapter(paymentStatusAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onPaymentStatusSelected(PaymentStatus.values()[position])
                }
            }
            actvCreateOrderPaymentMethod.apply {
                setAdapter(paymentMethodAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onPaymentMethodSelected(paymentMethodAdapter.getItem(position))
                }
            }
            tietCreateOrderPaymentAmount.apply {
                filters = arrayOf(decimalDigitsInputFilter)
                doAfterTextChanged { paymentAmountStr ->
                    viewModel.onPaymentAmountChanged(paymentAmountStr?.toString())
                }
            }
            tietCreateOrderPaymentDate.apply {
                inputType = InputType.TYPE_NULL
                setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        requestFocus()
                        showDatePickerDialog(true)
                    }
                    return@setOnTouchListener false
                }
            }
            tietCreateOrderPaymentTime.apply {
                inputType = InputType.TYPE_NULL
                setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        requestFocus()
                        showTimePickerDialog(true)
                    }
                    return@setOnTouchListener false
                }
            }
            fab.setThrottleClickListener { attemptCreateOrder() }
        }
    }

    override suspend fun render(state: CreateOrderViewState) {
        binding.apply {
            state.apply {
                actvCreateOrderClient.setText(clientTuple.name, false)
                clientAdapter.submitList(clientTupleList)
                actvCreateOrderAccount.setText(accountTuple.username, false)
                accountAdapter.submitList(accountTupleList)
                actvCreateOrderCharacter.setText(characterTuple.name, false)
                characterAdapter.submitList(characterTupleList)
                actvCreateOrderCategory.setText(category.title, false)
                categoryAdapter.submitList(categoryList)
                actvCreateOrderSubject.setText(subject.content, false)
                subjectAdapter.submitList(subjectList)
                tietCreateOrderStartDate.setText(startTime.formatDate("yyyy-MM-dd"))
                tietCreateOrderStartTime.setText(startTime.formatDate("HH:mm"))
                actvCreateOrderPaymentStatus.setText(getString(paymentStatus.resId), false)
                paymentStatusAdapter.submitList(paymentStatusList)
                tilCreateOrderPaymentMethod.isVisible = paymentStatus != PaymentStatus.UNPAID
                tilCreateOrderPaymentAmount.isVisible = paymentStatus != PaymentStatus.UNPAID
                tilCreateOrderPaymentDate.isVisible = paymentStatus != PaymentStatus.UNPAID
                tilCreateOrderPaymentTime.isVisible = paymentStatus != PaymentStatus.UNPAID
                tietCreateOrderPaymentDate.setText(paymentTime.formatDate("yyyy-MM-dd"))
                tietCreateOrderPaymentTime.setText(paymentTime.formatDate("HH:mm"))
                actvCreateOrderPaymentMethod.setText(getString(paymentMethod.resId), false)
                paymentMethodAdapter.submitList(paymentMethodList)
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateOrderSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateOrderSideEffect.ShowClientError ->
                    tilCreateOrderClient.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideClientError ->
                    tilCreateOrderClient.error = null
                is CreateOrderSideEffect.ShowAccountError ->
                    tilCreateOrderAccount.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideAccountError ->
                    tilCreateOrderAccount.error = null
                is CreateOrderSideEffect.ShowCharacterError ->
                    tilCreateOrderCharacter.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideCharacterError ->
                    tilCreateOrderCharacter.error = null
                is CreateOrderSideEffect.ShowCategoryError ->
                    tilCreateOrderCategory.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideCategoryError ->
                    tilCreateOrderCategory.error = null
                is CreateOrderSideEffect.ShowSubjectError ->
                    tilCreateOrderSubject.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideSubjectError ->
                    tilCreateOrderSubject.error = null
                is CreateOrderSideEffect.ShowTotalAmountError ->
                    tilCreateOrderTotalAmount.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HideTotalAmountError ->
                    tilCreateOrderTotalAmount.error = null
                is CreateOrderSideEffect.ShowTotalAmountHelper ->
                    tilCreateOrderTotalAmount.helperText = getString(sideEffect.resId)
                CreateOrderSideEffect.HideTotalAmountHelper ->
                    tilCreateOrderTotalAmount.helperText = null
                is CreateOrderSideEffect.ShowPaymentAmountError ->
                    tilCreateOrderPaymentAmount.error = getString(sideEffect.resId)
                CreateOrderSideEffect.HidePaymentAmountError ->
                    tilCreateOrderPaymentAmount.error = null
                is CreateOrderSideEffect.ShowPaymentAmountHelper ->
                    tilCreateOrderPaymentAmount.helperText = getString(sideEffect.resId)
                CreateOrderSideEffect.HidePaymentAmountHelper ->
                    tilCreateOrderPaymentAmount.helperText = null
                is CreateOrderSideEffect.CreateOrderSuccess -> {
                    resetUI()
                    root.showSnack(R.string.create_order_success)
                }
                is CreateOrderSideEffect.CreateOrderFailed ->
                    root.showSnack(R.string.error_create_order)
            }
        }
    }

    private fun showDatePickerDialog(isPayment: Boolean) {
        val selectionDate = if (isPayment) viewModel.paymentDate else viewModel.startDate
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(selectionDate.timeInMillis)
            .setTheme(com.yizhenwind.rocket.core.framework.R.style.Theme_Rocket_DatePicker)
            .build()

        datePicker.addOnPositiveButtonClickListener {
            viewModel.onDateSelected(isPayment, it)
        }

        datePicker.show(childFragmentManager, TAG_DATE_PICKER)
    }

    private fun showTimePickerDialog(isPayment: Boolean) {
        val selectionDate = if (isPayment) viewModel.paymentDate else viewModel.startDate
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setHour(selectionDate.get(Calendar.HOUR_OF_DAY))
            .setMinute(selectionDate.get(Calendar.MINUTE))
            .setTheme(com.yizhenwind.rocket.core.framework.R.style.Theme_Rocket_TimePicker)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            viewModel.onTimeSelected(isPayment, timePicker.hour, timePicker.minute)
        }

        timePicker.show(childFragmentManager, TAG_TIME_PICKER)
    }

    private fun attemptCreateOrder() {
        binding.apply {
            val remark = tietCreateOrderRemark.text?.toString()
            viewModel.attemptCreateOrder(remark)
        }
    }

    private fun resetUI() {
        viewModel.reset()
        binding.apply {
            tietCreateOrderTotalAmount.text = null
            tietCreateOrderPaymentAmount.text = null
            tietCreateOrderRemark.text = null
        }
    }

    override fun onDestroyView() {
        binding.apply {
            actvCreateOrderClient.setAdapter(null)
            actvCreateOrderAccount.setAdapter(null)
            actvCreateOrderCharacter.setAdapter(null)
            actvCreateOrderCategory.setAdapter(null)
            actvCreateOrderSubject.setAdapter(null)
            actvCreateOrderPaymentStatus.setAdapter(null)
            actvCreateOrderPaymentMethod.setAdapter(null)
        }
        super.onDestroyView()
    }

    companion object {

        private const val TAG_DATE_PICKER = "DATE_PICKER"

        private const val TAG_TIME_PICKER = "TIME_PICKER"

    }
}