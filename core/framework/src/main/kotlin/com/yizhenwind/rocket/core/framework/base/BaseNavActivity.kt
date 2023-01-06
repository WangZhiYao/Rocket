package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
abstract class BaseNavActivity : BaseActivity() {

    protected abstract val navController: NavController
    protected open val appBarConfiguration = AppBarConfiguration.Builder()
        .setFallbackOnNavigateUpListener {
            finish()
            true
        }
        .build()

    protected open fun setupToolbar(
        toolbar: Toolbar,
        appBarConfiguration: AppBarConfiguration = this.appBarConfiguration
    ) {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    protected open fun setNavGraph(
        @NavigationRes navGraphId: Int,
        startDestinationArgs: Bundle? = null
    ) {
        navController.setGraph(navGraphId, startDestinationArgs)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}