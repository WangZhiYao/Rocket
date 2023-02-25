package com.yizhenwind.rocket.feature.order.ui.widget

import com.yizhenwind.rocket.core.framework.widget.BaseOneLineTextDropDownAdapter
import com.yizhenwind.rocket.core.model.PaymentMethod

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class PaymentMethodDropDownAdapter : BaseOneLineTextDropDownAdapter<PaymentMethod>() {

    override fun itemToString(item: PaymentMethod): CharSequence = item.name

}