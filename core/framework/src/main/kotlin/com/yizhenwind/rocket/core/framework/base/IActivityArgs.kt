package com.yizhenwind.rocket.core.framework.base

import android.content.Context
import android.content.Intent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IActivityArgs {

    /**
     * @return returns an intent that can be used to launch this activity.
     */
    fun intent(context: Context): Intent

    /**
     * Launches the activity given your activity context.
     *
     * The default implementation uses the intent generated from [intent]
     */
    fun launch(context: Context) = context.startActivity(intent(context))

}