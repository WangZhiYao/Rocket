package com.yizhenwind.rocket.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
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
        AppBarConfiguration.Builder(setOf(R.id.nav_home, R.id.nav_client_profile, R.id.nav_user))
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
            setupToolbar(appBar.toolbar)
            appBar.toolbar.apply {
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener { menuItem ->
                    if (menuItem.itemId == R.id.action_search) {
                        return@setOnMenuItemClickListener true
                    }
                    return@setOnMenuItemClickListener false
                }
            }
            navView.setupWithNavController(navController)
        }
    }
}