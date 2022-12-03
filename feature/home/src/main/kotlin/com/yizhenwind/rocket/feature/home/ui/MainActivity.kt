package com.yizhenwind.rocket.feature.home.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.feature.home.R
import com.yizhenwind.rocket.feature.home.databinding.ActivityMainBinding
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

    override val toolbar: Toolbar by lazy { binding.navContent.toolbar }
    override val navController: NavController by lazy {
        binding.navContent.navHostFragment.getFragment<NavHostFragment>().navController
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_customer_list),
            binding.drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    override fun initView() {
        super.initView()
        binding.apply {
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            navContent.fab.apply {
                setImageResource(R.drawable.ic_round_add_white_24dp)
            }
        }
    }

    override fun getNavGraph(): Int = R.navigation.navigation_main

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}