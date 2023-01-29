package com.yizhenwind.rocket.feature.contacttype.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.contacttype.R
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

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                findNavController().navigate(ContactTypeFragmentDirections.actionToCreateContactType())
            }
        }
        adapter.onDeleteClickListener = { contactType ->
            viewModel.toggleContactTypeState(contactType, false)
        }
    }

    override suspend fun render(state: ContactTypeViewState) {
        adapter.submitList(state.contactTypeList)
    }

    override fun handleSideEffect(sideEffect: ContactTypeSideEffect) {
        binding.apply {
            when (sideEffect) {
                is ContactTypeSideEffect.DeleteContactTypeSuccess -> {
                    sideEffect.contactType.apply {
                        root.showSnack(
                            text = getString(R.string.create_contact_type_delete_success, name),
                            anchorView = fab,
                            actionText = getString(R.string.create_contact_type_delete_revoke)
                        ) {
                            viewModel.toggleContactTypeState(this, true)
                        }
                    }
                }
            }
        }
    }
}