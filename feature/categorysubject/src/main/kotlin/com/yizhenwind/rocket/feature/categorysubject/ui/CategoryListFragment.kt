package com.yizhenwind.rocket.feature.categorysubject.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.common.constant.DeepLink
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.MessageDialog
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.feature.categorysubject.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
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
        adapter.apply {
            onItemClickListener = { category ->
                navigate {
                    module(DeepLink.Module.SUBJECT)
                    path(DeepLink.Path.LIST)
                    arguments(DeepLink.Arguments.CATEGORY_ID, category.id.toString())
                    arguments(DeepLink.Arguments.CATEGORY_TITLE, category.title)
                }
            }
            onDeleteClickListener = { category ->
                showDeleteCategoryDialog(category)
            }
        }
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                navigate {
                    module(DeepLink.Module.CATEGORY)
                    path(DeepLink.Path.CREATE)
                }
            }
        }
    }

    override suspend fun render(state: CategoryListViewState) {
        adapter.submitList(state.categoryList)
    }

    override fun handleSideEffect(sideEffect: CategoryListSideEffect) {
        binding.apply {
            when (sideEffect) {
                CategoryListSideEffect.DeleteCategorySuccess ->
                    root.showSnack(R.string.category_list_delete_category_success, anchorView = fab)
                is CategoryListSideEffect.ShowError ->
                    root.showSnack(sideEffect.resId, anchorView = fab)
            }
        }
    }

    private fun showDeleteCategoryDialog(category: Category) {
        MessageDialog.Builder(childFragmentManager)
            .setTitle(getString(R.string.dialog_delete_category_title))
            .setContent(getString(R.string.dialog_delete_category_content, category.title))
            .setPositive(getString(R.string.dialog_delete_category_confirm))
            .setOnPositiveClickListener { dialog ->
                viewModel.deleteCategory(category)
                dialog.dismiss()
            }
            .show()
    }

}