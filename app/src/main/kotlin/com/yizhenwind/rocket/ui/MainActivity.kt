package com.yizhenwind.rocket.ui

import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.NavigationMainDirections
import com.yizhenwind.rocket.R
import com.yizhenwind.rocket.core.framework.base.BaseBottomAppBarActivity
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.ui.widget.MainBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseBottomAppBarActivity() {

    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_client_list)
        )
    }

    override fun init() {
        binding.apply {
            setupToolbar(toolbar, appBarConfiguration)
            fab.apply {
                setImageResource(R.drawable.ic_round_add_white_24dp)
                setThrottleClickListener {
                    when (navController.currentDestination?.id) {
                        R.id.nav_client_list -> {
                            navController.navigate(
                                NavigationMainDirections.actionNavClientListToNavClientNav()
                            )
                        }
                        else -> Snackbar.make(root, "Action", Snackbar.LENGTH_SHORT)
                            .setAnchorView(it)
                            .show()
                    }
                }
            }
            bottomAppBar.apply {
                setNavigationIcon(R.drawable.ic_round_menu_white_24dp)
                inflateMenu(R.menu.menu_main_bottom_app_bar)
                setNavigationOnClickListener {
                    MainBottomSheetDialog().show(supportFragmentManager)
                }
            }
        }
        setNavGraph(R.navigation.navigation_main)
    }
}