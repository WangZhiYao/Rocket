package com.yizhenwind.rocket.feature.categorysubject.ui.subject.create

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.CategoryDropDownAdapter
import com.yizhenwind.rocket.feature.categorysubject.R
import com.yizhenwind.rocket.feature.categorysubject.databinding.FragmentCreateSubjectBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
@AndroidEntryPoint
class CreateSubjectFragment :
    BaseFragment<FragmentCreateSubjectBinding>(FragmentCreateSubjectBinding::inflate),
    IMVIHost<CreateSubjectViewState, CreateSubjectSideEffect> {

    private val viewModel by viewModels<CreateSubjectViewModel>()
    private val navArgs by navArgs<CreateSubjectFragmentArgs>()

    private val categoryAdapter by lazy {
        CategoryDropDownAdapter(requireContext())
    }

    override fun init() {
        initData()
        initView()
    }

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            initViewState(navArgs.categoryId)
        }
    }

    override fun initView() {
        binding.apply {
            actvCreateSubjectCategory.apply {
                setAdapter(categoryAdapter)
                setOnItemClickListener { _, _, position, _ ->
                    viewModel.onCategorySelected(categoryAdapter.getItem(position))
                }
            }
            tietCreateSubjectContent.doAfterTextChanged { content ->
                viewModel.onContentChanged(content?.toString())
            }
            fab.setThrottleClickListener { attemptCreateSubject() }
        }
    }

    override suspend fun render(state: CreateSubjectViewState) {
        binding.apply {
            state.apply {
                categoryAdapter.submitList(categoryList)
                actvCreateSubjectCategory.setText(category.title, false)
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateSubjectSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateSubjectSideEffect.ShowCategoryError ->
                    tilCreateSubjectCategory.error = getString(sideEffect.resId)
                CreateSubjectSideEffect.HideCategoryError ->
                    tilCreateSubjectCategory.error = null
                is CreateSubjectSideEffect.ShowContentError ->
                    tilCreateSubjectContent.error = getString(sideEffect.resId)
                CreateSubjectSideEffect.HideContentError ->
                    tilCreateSubjectContent.error = null
                is CreateSubjectSideEffect.CreateSubjectSuccess -> {
                    resetUI()
                    root.showSnack(R.string.create_subject_success)
                }
                is CreateSubjectSideEffect.CreateSubjectFailed ->
                    root.showSnack(sideEffect.resId)
            }
        }
    }

    private fun attemptCreateSubject() {
        val content = binding.tietCreateSubjectContent.text?.toString()
        viewModel.attemptCreateSubject(content)
    }

    private fun resetUI() {
        binding.tietCreateSubjectContent.text = null
    }
}