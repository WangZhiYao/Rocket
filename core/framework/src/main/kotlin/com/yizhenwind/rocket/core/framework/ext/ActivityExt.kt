package com.yizhenwind.rocket.core.framework.ext

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.lazy.ActivityArgsLazy
import com.yizhenwind.rocket.core.framework.lazy.ViewBindingLazy

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/3/2
 */
inline fun <reified VB : ViewBinding> Activity.viewBindings(): ViewBindingLazy<VB> =
    ViewBindingLazy(VB::class) { layoutInflater }

inline fun <reified Args : IActivityArgs> Activity.activityArgs(): ActivityArgsLazy<Args> =
    ActivityArgsLazy(Args::class) {
        intent ?: throw IllegalStateException("${this::class.java.simpleName} has a null Intent")
    }