package com.yizhenwind.rocket.ui.setting

import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.NavigationMainDirections
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override fun init() {
        binding.apply {
            tvSettingContactType.setThrottleClickListener {
                findNavController().navigate(NavigationMainDirections.actionToContactType())
            }
        }
    }

}