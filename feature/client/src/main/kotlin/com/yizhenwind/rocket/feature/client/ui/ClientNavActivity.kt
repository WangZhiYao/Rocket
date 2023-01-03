package com.yizhenwind.rocket.feature.client.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseNavBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings
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

    private val binding by viewBindings<ActivityBaseNavBinding>()
    private val navArgs by navArgs<ClientNavActivityArgs>()

    override val navController by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>().navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initData()
    }

    private fun initView() {
        setupToolbar(binding.toolbar)
        setNavGraph(R.navigation.navigation_client)
    }

    private fun initData() {
        navArgs.navUri?.apply {
            if (navController.currentDestination?.hasDeepLink(this) == false) {
                navController.navigate(this)
            }
        }
    }
}