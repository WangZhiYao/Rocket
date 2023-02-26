package com.yizhenwind.rocket.feature.category.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.feature.category.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
@AndroidEntryPoint
class CategoryListFragment : BaseListFragment(),
    IMVIHost<CategoryListViewState, CategoryListSideEffect> {

    private val viewModel by viewModels<CategoryListViewModel>()

    override val adapter = CategoryAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    override fun initView() {
        super.initView()
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                findNavController().navigate(CategoryListFragmentDirections.actionCategoryListToCreateCategory())
            }
        }
        adapter.apply {
            onItemClickListener = { category ->

            }
            onDeleteClickListener = { category ->
                viewModel.deleteCategory(category)
            }
        }
    }

    override suspend fun render(state: CategoryListViewState) {
        adapter.submitList(state.categoryList)
    }

    override fun handleSideEffect(sideEffect: CategoryListSideEffect) {
        binding.apply {
            when (sideEffect) {
                is CategoryListSideEffect.DeleteCategorySuccess ->
                    root.showSnack(
                        text = getString(R.string.category_delete_success),
                        anchorView = fab
                    )
            }
        }
    }

}