package com.yizhenwind.rocket.feature.categorysubject.ui.create

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.categorysubject.R
import com.yizhenwind.rocket.feature.categorysubject.databinding.FragmentCreateCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
@AndroidEntryPoint
class CreateCategoryFragment :
    BaseFragment<FragmentCreateCategoryBinding>(FragmentCreateCategoryBinding::inflate),
    IMVIHost<CreateCategoryViewState, CreateCategorySideEffect> {

    private val viewModel by viewModels<CreateCategoryViewModel>()

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        binding.apply {
            tietCreateCategoryTitle.doAfterTextChanged { title ->
                viewModel.onTitleChanged(title?.toString())
            }
            fab.setThrottleClickListener { attemptCreateCategory() }
        }
    }

    override fun handleSideEffect(sideEffect: CreateCategorySideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateCategorySideEffect.ShowTitleError ->
                    tilCreateCategoryTitle.error = getString(sideEffect.resId)
                CreateCategorySideEffect.HideTitleError ->
                    tilCreateCategoryTitle.error = null
                is CreateCategorySideEffect.CreateCategorySuccess -> {
                    resetUI()
                    root.showSnack(
                        R.string.create_category_success,
                        Snackbar.LENGTH_LONG,
                        R.string.create_category_success_to_create_subject
                    ) {
                        // TODO: create subject
                    }
                }
                is CreateCategorySideEffect.ShowSnack ->
                    root.showSnack(sideEffect.resId)
            }
        }
    }

    private fun attemptCreateCategory() {
        binding.apply {
            val title = tietCreateCategoryTitle.text?.toString()
            val remark = tietCreateClientRemark.text?.toString()
            viewModel.createCategory(title, remark)
        }
    }

    private fun resetUI() {
        binding.apply {
            tietCreateCategoryTitle.text = null
            tietCreateClientRemark.text = null
        }
    }
}