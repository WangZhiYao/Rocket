package com.yizhenwind.rocket.feature.contacttype.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.contacttype.R
import com.yizhenwind.rocket.feature.contacttype.ui.widget.CreateContactTypeDialog
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
@AndroidEntryPoint
class ContactTypeFragment : BaseListFragment(),
    IMVIHost<ContactTypeViewState, ContactTypeSideEffect> {

    private val viewModel by viewModels<ContactTypeViewModel>()

    override val adapter = ContactTypeAdapter()

    private val createContactTypeDialog by lazy {
        CreateContactTypeDialog.Builder(childFragmentManager)
            .setOnNameChangeListener { name -> viewModel.onNameChanged(name) }
            .setOnPositiveClickListener { name -> viewModel.createContactType(name) }
            .build()
    }

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                createContactTypeDialog.show()
            }
        }
        adapter.onItemClickListener = {
            // TODO: on contact type clicked
        }
    }

    override suspend fun render(state: ContactTypeViewState) {
        adapter.submitList(state.contactTypeList)
    }

    override fun handleSideEffect(sideEffect: ContactTypeSideEffect) {
        when (sideEffect) {
            is ContactTypeSideEffect.ShowError -> createContactTypeDialog.setError(sideEffect.resId)
            ContactTypeSideEffect.HideError -> createContactTypeDialog.hideError()
            is ContactTypeSideEffect.ShowSnake -> {
                createContactTypeDialog.dismiss()
                binding.root.showSnack(
                    resId = sideEffect.resId,
                    anchorView = binding.fab
                )
            }
        }
    }

}