package com.yizhenwind.rocket.feature.category.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.feature.category.databinding.ItemCategoryBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
class CategoryAdapter : BaseListAdapter<Category, CategoryViewHolder>(Category.COMPARATOR) {

    var onDeleteClickListener: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(parent.viewBinding(ItemCategoryBinding::inflate)).apply {
            onItemClickListener = { category ->
                this@CategoryAdapter.onItemClickListener?.invoke(category)
            }
            onDeleteClickListener = { category ->
                this@CategoryAdapter.onDeleteClickListener?.invoke(category)
            }
        }

}