package com.yizhenwind.rocket.core.common.constant

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.common.R

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
enum class PaymentMethod(@StringRes val resId: Int) {

    ALIPAY(R.string.payment_method_alipay),

    WECHAT(R.string.contact_type_wechat),

    QQ(R.string.payment_method_qq)

}