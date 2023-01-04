package com.yizhenwind.rocket.feature.client.ui.composite.character

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.rocket.core.framework.base.BaseMVIListFragment
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeViewModel
import com.yizhenwind.rocket.feature.client.ui.composite.ClientCompositeViewState
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/20
 */
@AndroidEntryPoint
class ClientCharacterFragment :
    BaseMVIListFragment<ClientCharacterViewState, ClientCharacterSideEffect>() {

    private val clientCompositeViewModel by activityViewModels<ClientCompositeViewModel>()
    private val viewModel by viewModels<ClientCharacterViewModel>()

    private val adapter by lazy { ClientCharacterAdapter() }

    override fun init() {
        initData()
        super.init()
    }

    private fun initData() {
        clientCompositeViewModel.observe(viewLifecycleOwner, state = ::observeClient)
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    private fun observeClient(state: ClientCompositeViewState) {
        viewModel.observeCharacterListByClientId(state.client.id)
    }

    override fun render(state: ClientCharacterViewState) {
        adapter.submitList(state.characterList)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(requireContext())

    override fun getAdapter(): RecyclerView.Adapter<*> = adapter

}