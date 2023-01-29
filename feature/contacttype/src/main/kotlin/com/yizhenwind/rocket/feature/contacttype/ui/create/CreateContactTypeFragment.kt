package com.yizhenwind.rocket.feature.contacttype.ui.create

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.yizhenwind.rocket.core.framework.base.BaseFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.contacttype.databinding.FragmentCreateContactTypeBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
@AndroidEntryPoint
class CreateContactTypeFragment :
    BaseFragment<FragmentCreateContactTypeBinding>(FragmentCreateContactTypeBinding::inflate),
    IMVIHost<CreateContactTypeViewState, CreateContactTypeSideEffect> {

    private val viewModel by viewModels<CreateContactTypeViewModel>()

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, sideEffect = ::handleSideEffect)
    }

    private fun initView() {
        binding.apply {
            tietCreateContactType.apply {
                doAfterTextChanged { name ->
                    viewModel.onNameChanged(name?.toString())
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        viewModel.createContactType(text?.toString())
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
            }

            fab.setThrottleClickListener {
                viewModel.createContactType(tietCreateContactType.text?.toString())
            }
        }
    }

    override fun handleSideEffect(sideEffect: CreateContactTypeSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CreateContactTypeSideEffect.ShowError -> tilCreateContactType.error =
                    getString(sideEffect.resId)
                CreateContactTypeSideEffect.HideError -> tilCreateContactType.error = null
                is CreateContactTypeSideEffect.NavigationUp -> findNavController().navigateUp()
            }
        }
    }

}