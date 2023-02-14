package com.yizhenwind.rocket.core.common.constant

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.common.R

/**
 *
 * @author WangZhiYao
 * @since 2023/2/7
 */
enum class PaymentStatus(@StringRes val resId: Int) {

    UNPAID(R.string.payment_status_unpaid),

    PARTIALLY_PAID(R.string.payment_status_partially_paid),

    PAID(R.string.payment_status_paid)

}