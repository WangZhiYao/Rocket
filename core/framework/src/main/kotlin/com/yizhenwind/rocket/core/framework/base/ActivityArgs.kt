package com.yizhenwind.rocket.core.framework.base

import android.content.Context
import android.content.Intent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface ActivityArgs {

    fun intent(context: Context): Intent

    fun launch(context: Context) = context.startActivity(intent(context))

}