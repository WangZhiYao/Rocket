package com.yizhenwind.rocket.core.framework.ext

import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.lazy.FragmentArgsLazy

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/22
 */
inline fun <reified Args : IFragmentArgs> Fragment.fragmentArgs(): FragmentArgsLazy<Args> =
    FragmentArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("${this::class.java.simpleName} has null arguments")
    }