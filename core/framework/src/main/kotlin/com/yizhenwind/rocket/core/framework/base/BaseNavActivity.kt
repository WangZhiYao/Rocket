package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.yizhenwind.rocket.core.framework.databinding.LayoutBaseNavBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/3
 */
abstract class BaseNavActivity : BaseActivity() {

    private val binding by viewBindings<LayoutBaseNavBinding>()
    protected val navController by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>().navController
    }
    protected val appBarConfiguration = AppBarConfiguration.Builder()
        .setFallbackOnNavigateUpListener {
            finish()
            true
        }
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    protected open fun initView() {
        binding.apply {
            setSupportActionBar(toolbar)
            navController.apply {
                graph = navInflater.inflate(getNavGraph())
            }
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    @NavigationRes
    abstract fun getNavGraph(): Int

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}