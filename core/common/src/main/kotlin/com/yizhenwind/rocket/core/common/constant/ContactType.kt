package com.yizhenwind.rocket.core.common.constant

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.common.R

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/25
 */
enum class ContactType(@StringRes val resId: Int) {

    QQ(R.string.contact_type_qq),

    WECHAT(R.string.contact_type_wechat),

    PHONE(R.string.contact_type_phone)

}