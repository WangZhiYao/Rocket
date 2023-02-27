package com.yizhenwind.rocket.feature.categorysubject.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.common.constant.DeepLink
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
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
    IMVIHost<CategoryListViewState, Nothing> {

    private val viewModel by viewModels<CategoryListViewModel>()

    override val adapter = CategoryAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        adapter.apply {
            onItemClickListener = { category ->

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