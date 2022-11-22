package com.yizhenwind.rocket.feature.client.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.rocket.core.framework.base.BaseMVIFragment
import com.yizhenwind.rocket.feature.client.databinding.FragmentClientListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
@AndroidEntryPoint
class ClientListFragment :
    BaseMVIFragment<FragmentClientListBinding, ClientListViewState, ClientListSideEffect>(
        FragmentClientListBinding::inflate
    ) {

    private val viewModel by viewModels<ClientListViewModel>()
    private val adapter by lazy { ClientProfileAdapter() }

    override fun init() {
        initData()
        initView()
    }

    private fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun initView() {
        adapter.apply {
            onClientProfileClickListener = { clientProfile ->

            }

            onClientProfileActionClickListener = { clientProfile ->

            }
        }

        binding.apply {
            rvClientProfile.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@ClientListFragment.adapter
            }
        }
    }

    override fun render(state: ClientListViewState) {
        lifecycleScope.launch {
            adapter.submitData(state.clientProfileList)
        }
    }
}