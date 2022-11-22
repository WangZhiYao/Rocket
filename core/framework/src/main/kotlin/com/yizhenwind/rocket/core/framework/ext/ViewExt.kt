package com.yizhenwind.rocket.core.framework.ext

import android.view.View
import com.yizhenwind.rocket.core.framework.widget.OnThrottleClickListener

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/7
 */
fun View.setThrottleClickListener(throttleClickListener: (v: View?) -> Unit) {
    setThrottleClickListener(throttleClickListener, 500)
}

fun View.setThrottleClickListener(throttleClickListener: (v: View?) -> Unit, millis: Long = 500) {
    setOnClickListener(object : OnThrottleClickListener(millis) {
        override fun onSingleClick(v: View?) {
            throttleClickListener(v)
        }
    })
}