package com.yizhenwind.rocket.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.NavigationMainDirections
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseNavActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    override val navController: NavController
        get() = binding.appBar.contentMain.navHostFragment.getFragment<NavHostFragment>().navController

    override val appBarConfiguration by lazy {
        AppBarConfiguration.Builder(setOf(R.id.nav_home, R.id.nav_client_profile, R.id.nav_user, R.id.nav_setting))
            .setOpenableLayout(binding.drawerLayout)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            appBar.apply {
                setupToolbar(toolbar)
                fab.setThrottleClickListener {
                    when (navController.currentDestination?.id) {
                        R.id.nav_client_profile -> {
                            navController.navigate(NavigationMainDirections.actionToCreateClient())
                        }
                    }
                }
            }
            navView.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                appBar.fab.apply {
                    isVisible = destination.id != R.id.nav_setting
                    setImageResource(
                        when (destination.id) {
                            R.id.nav_client_profile -> R.drawable.ic_round_add_white_24dp
                            else -> R.drawable.ic_round_faces_white_24dp
                        }
                    )
                }
            }
        }
    }
}