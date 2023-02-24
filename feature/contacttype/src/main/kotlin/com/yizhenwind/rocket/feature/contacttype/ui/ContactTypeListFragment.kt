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
class ContactTypeListFragment : BaseListFragment(),
    IMVIHost<ContactTypeListViewState, ContactTypeListSideEffect> {

    private val viewModel by viewModels<ContactTypeListViewModel>()

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
                findNavController().navigate(ContactTypeListFragmentDirections.actionToCreateContactType())
            }
        }
        adapter.onDeleteClickListener = { contactType ->
            viewModel.updateContactType(contactType.copy(enable = false))
        }
    }

    override suspend fun render(state: ContactTypeListViewState) {
        adapter.submitList(state.contactTypeList)
    }

    override fun handleSideEffect(sideEffect: ContactTypeListSideEffect) {
        binding.apply {
            when (sideEffect) {
                is ContactTypeListSideEffect.DeleteContactTypeSuccess ->
                    sideEffect.contactType.apply {
                        root.showSnack(
                            text = getString(R.string.contact_type_delete_success, name),
                            anchorView = fab,
                            actionText = getString(R.string.contact_type_delete_revoke)
                        ) {
                            viewModel.updateContactType(copy(enable = true))
                        }
                    }
            }
        }
    }
}