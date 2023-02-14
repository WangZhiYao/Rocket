package com.yizhenwind.rocket.core.common.constant

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.common.R

/**
 *
 * @author WangZhiYao
 * @since 2023/2/7
 */
enum class OrderStatus(@StringRes val resId: Int) {

    NOT_STARTED(R.string.order_status_not_started),

    PROCESSING(R.string.order_status_processing),

    PAUSE(R.string.order_status_pause),

    COMPLETED(R.string.order_status_complete),

    REFUND(R.string.order_status_refund)

}