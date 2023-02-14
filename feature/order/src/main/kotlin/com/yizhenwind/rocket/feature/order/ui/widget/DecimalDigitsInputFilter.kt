package com.yizhenwind.rocket.feature.order.ui.widget

import android.text.InputFilter
import android.text.Spanned

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class DecimalDigitsInputFilter(private val maxDecimalPlaces: Int = 2) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        // 首位为.则补0
        if (source == ".") {
            if (dest.isBlank()) {
                return "0."
            } else {
                // 如果往最大小数位数前插入.则无效
                if (dstart < dest.length - maxDecimalPlaces) {
                    return ""
                }
            }
        }

        // 限制最大小数位数
        val temp = dest.split(".")
        if (temp.size > 1) {
            val decimal = temp[1]
            if (decimal.length == maxDecimalPlaces) {
                return ""
            }
        }

        return source
    }
}