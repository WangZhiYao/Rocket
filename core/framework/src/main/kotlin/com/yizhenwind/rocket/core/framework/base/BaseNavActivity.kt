package com.yizhenwind.rocket.core.framework.base

import androidx.annotation.NavigationRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
abstract class BaseNavActivity : BaseActivity() {

    protected abstract val toolbar: Toolbar
    protected abstract val navController: NavController

    protected open fun initView() {
        setSupportActionBar(toolbar)
        navController.apply {
            graph = navInflater.inflate(getNavGraph())
        }
    }

    @NavigationRes
    abstract fun getNavGraph(): Int
}