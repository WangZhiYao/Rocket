package com.yizhenwind.rocket.feature.order.ui.widget

import android.content.Context
import com.yizhenwind.rocket.core.common.constant.PaymentMethod
import com.yizhenwind.rocket.core.framework.widget.BaseOneLineTextDropDownAdapter

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class PaymentMethodDropDownAdapter(private val context: Context) :
    BaseOneLineTextDropDownAdapter<PaymentMethod>() {

    override fun itemToString(item: PaymentMethod): CharSequence = context.getString(item.resId)

}