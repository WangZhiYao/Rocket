package com.yizhenwind.rocket.feature.setting.ui

import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.mediator.contacttype.IContactTypeService
import com.yizhenwind.rocket.feature.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    @Inject
    lateinit var contactTypeService: IContactTypeService

    @Inject
    lateinit var categoryService: ICategoryService

    override fun init() {
        initView()
    }

    override fun initView() {
        binding.apply {
            llSettingContactType.setThrottleClickListener {
                contactTypeService.launchContactTypeList(requireContext())
            }

            llSettingCategory.setThrottleClickListener {
                categoryService.launchCategoryList(requireContext())
            }
        }
    }
}