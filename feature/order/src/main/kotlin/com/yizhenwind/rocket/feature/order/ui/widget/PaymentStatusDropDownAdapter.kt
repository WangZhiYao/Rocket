package com.yizhenwind.rocket.feature.order.ui.widget

import android.content.Context
import com.yizhenwind.rocket.core.common.constant.PaymentStatus
import com.yizhenwind.rocket.core.framework.widget.BaseOneLineTextDropDownAdapter

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class PaymentStatusDropDownAdapter(private val context: Context) :
    BaseOneLineTextDropDownAdapter<PaymentStatus>() {

    override fun itemToString(item: PaymentStatus): CharSequence =
        context.getString(item.resId)

}