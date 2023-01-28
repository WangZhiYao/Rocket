package com.yizhenwind.rocket.core.framework.base

import androidx.annotation.NavigationRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
abstract class BaseNavActivity : BaseActivity() {

    protected abstract val navController: NavController
    protected open val appBarConfiguration = AppBarConfiguration.Builder()
        .setFallbackOnNavigateUpListener {
            finish()
            true
        }
        .build()

    protected fun setupToolbar(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    protected open fun setupNavGraph(@NavigationRes resId: Int) {
        navController.apply {
            graph = navInflater.inflate(resId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}