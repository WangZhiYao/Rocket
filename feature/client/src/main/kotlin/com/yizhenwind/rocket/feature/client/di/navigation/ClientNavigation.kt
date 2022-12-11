package com.yizhenwind.rocket.feature.client.di.navigation

import android.content.Context
import android.net.Uri
import com.yizhenwind.rocket.core.mediator.client.navigation.IClientNavigation
import com.yizhenwind.rocket.feature.client.ui.ClientNavArgs
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
class ClientNavigation @Inject constructor() : IClientNavigation {

    override fun launch(context: Context, navUri: Uri?) {
        ClientNavArgs(navUri).launch(context)
    }

}