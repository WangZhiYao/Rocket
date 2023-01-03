package com.yizhenwind.rocket.core.framework.ext

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
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

fun View.makeSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) = makeSnack(resources.getText(resId), duration)

fun View.makeSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) = Snackbar.make(this, text, duration)

fun View.showSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) {
    showSnack(resources.getText(resId), duration)
}

fun View.showSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) {
    makeSnack(text, duration).show()
}

fun View.showSnackWithAction(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    showSnackWithAction(
        resources.getText(resId),
        duration,
        resources.getText(actionResId),
        listener
    )
}

fun View.showSnackWithAction(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    actionText: CharSequence,
    listener: View.OnClickListener
) {
    makeSnack(text, duration)
        .setAction(actionText, listener)
        .setActionTextColor(ContextCompat.getColor(context, R.color.color_secondary))
        .show()
}