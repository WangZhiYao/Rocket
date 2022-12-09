package com.yizhenwind.rocket.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.base.BaseActivity
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
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()
    private val navController by lazy {
        binding.navContent.navHostFragment.getFragment<NavHostFragment>().navController
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_client_list),
            binding.drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            setSupportActionBar(navContent.toolbar)
            navController.apply {
                graph = navInflater.inflate(R.navigation.navigation_main)
                setupActionBarWithNavController(this, appBarConfiguration)
                navView.setupWithNavController(this)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}