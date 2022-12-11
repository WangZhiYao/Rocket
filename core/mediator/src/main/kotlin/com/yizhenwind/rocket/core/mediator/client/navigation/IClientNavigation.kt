package com.yizhenwind.rocket.core.mediator.client.navigation

import android.content.Context
import android.net.Uri

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
interface IClientNavigation {

    fun launch(context: Context, navUri: Uri? = null)

}