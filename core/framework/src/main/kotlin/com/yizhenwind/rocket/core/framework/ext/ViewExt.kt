package com.yizhenwind.rocket.core.framework.ext

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.R
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

fun View.showSnack(@StringRes resId: Int) {
    showSnack(resId, Snackbar.LENGTH_SHORT)
}

fun View.showSnack(text: String) {
    showSnack(text, Snackbar.LENGTH_SHORT)
}

fun View.showSnack(@StringRes resId: Int, duration: Int) {
    Snackbar.make(this, resId, duration).show()
}

fun View.showSnack(text: String, duration: Int) {
    Snackbar.make(this, text, duration).show()
}

fun View.showSnackWithAction(
    @StringRes resId: Int,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    showSnackWithAction(resId, Snackbar.LENGTH_SHORT, actionResId, listener)
}

fun View.showSnackWithAction(
    @StringRes resId: Int,
    duration: Int,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    Snackbar.make(this, resId, duration).setAction(actionResId, listener)
        .setActionTextColor(ContextCompat.getColor(context, R.color.color_secondary)).show()
}