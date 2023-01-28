package com.yizhenwind.rocket.feature.contacttype.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseNavBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.feature.contacttype.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@AndroidEntryPoint
class ContactTypeNavActivity: BaseNavActivity() {

    private val binding by viewBindings<ActivityBaseNavBinding>()

    override val navController: NavController
        get() = binding.navHostFragment.getFragment<NavHostFragment>().navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            setupToolbar(toolbar)
        }
        setupNavGraph(R.navigation.navigation_contact_type)
    }

}