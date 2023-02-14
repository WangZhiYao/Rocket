package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.feature.setting.databinding.FragmentSettingBinding
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
        initView()
    }

    override fun initView() {
        binding.apply {
            tvSettingContactType.setThrottleClickListener {
                /*findNavController().navigate(NavigationMainDirections.actionToContactType())*/
            }
        }
    }
}