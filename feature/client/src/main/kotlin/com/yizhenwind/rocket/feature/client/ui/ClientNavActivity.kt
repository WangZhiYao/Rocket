package com.yizhenwind.rocket.feature.client.ui

import android.os.Bundle
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.ext.activityArgs
import com.yizhenwind.rocket.feature.client.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/10
 */
@AndroidEntryPoint
class ClientNavActivity : BaseNavActivity() {

    private val args by activityArgs<ClientNavArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.navUri?.apply {
            if (navController.currentDestination?.hasDeepLink(this) == false) {
                navController.navigate(this)
            }
        }
    }

    override fun getNavGraph(): Int = R.navigation.navigation_client

}