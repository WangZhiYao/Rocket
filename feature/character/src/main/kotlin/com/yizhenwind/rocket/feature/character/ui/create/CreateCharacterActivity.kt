package com.yizhenwind.rocket.feature.character.ui.create

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.yizhenwind.rocket.core.framework.base.BaseNavActivity
import com.yizhenwind.rocket.core.framework.databinding.ActivityBaseNavBinding
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.feature.character.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
@AndroidEntryPoint
class CreateCharacterActivity: BaseNavActivity() {

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
        navController.setGraph(R.navigation.navigation_create_character, intent.extras)
    }

}