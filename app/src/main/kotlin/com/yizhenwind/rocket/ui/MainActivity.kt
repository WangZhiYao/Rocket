package com.yizhenwind.rocket.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.common.route.RouteAction
import com.yizhenwind.rocket.core.common.route.RouteModule
import com.yizhenwind.rocket.core.common.route.route
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.core.mediator.client.navigation.IClientNavigation
import com.yizhenwind.rocket.databinding.ActivityMainBinding
import com.yizhenwind.rocket.ui.widget.MainBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()
    private val navController by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>().navController
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_client_list)
        )
    }

    @Inject
    lateinit var clientNavigation: IClientNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            toolbar.setupWithNavController(navController, appBarConfiguration)
            fab.setThrottleClickListener {
                when (navController.currentDestination?.id) {
                    R.id.nav_client_list -> {
                        clientNavigation.launch(
                            this@MainActivity,
                            route {
                                module(RouteModule.CLIENT)
                                action(RouteAction.CREATE)
                            }
                        )
                    }
                    else -> Snackbar.make(root, "Action", Snackbar.LENGTH_SHORT)
                        .setAnchorView(it)
                        .show()
                }
            }
            bottomAppBar.setNavigationOnClickListener {
                MainBottomSheetDialog().show(supportFragmentManager)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}