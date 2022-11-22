package com.yizhenwind.rocket.core.framework.widget

import android.os.SystemClock
import android.view.View

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/7
 */
abstract class OnThrottleClickListener @JvmOverloads constructor(
    private val minClickInternal: Long = 500
) : View.OnClickListener {

    private var lastClickTime = 0L

    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        val currentClickTime = SystemClock.elapsedRealtime()
        val elapsedTime = currentClickTime - lastClickTime
        if (elapsedTime < minClickInternal) {
            return
        }
        lastClickTime = currentClickTime
        onSingleClick(v)
    }
}