package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseBottomAppbarBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
abstract class BaseBottomAppBarActivity : BaseNavActivity() {

    protected val binding by viewBindings<ActivityBaseBottomAppbarBinding>()
    override val navController by lazy {
        binding.navHostFragment.getFragment<NavHostFragment>().navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    abstract fun init()

}