package com.yizhenwind.rocket.core.framework.ext

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.yizhenwind.rocket.core.common.util.DeepLinkBuilder
import com.yizhenwind.rocket.core.framework.R
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
        arguments
            ?: throw IllegalStateException("${this::class.java.simpleName} has null arguments")
    }

fun Fragment.navigate(initializer: DeepLinkBuilder.() -> Unit) {
    findNavController().navigate(
        DeepLinkBuilder().apply(initializer).build(),
        navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    )
}

fun Fragment.navigate(deepLink: Uri) {
    findNavController().navigate(
        deepLink,
        navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    )
}

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}