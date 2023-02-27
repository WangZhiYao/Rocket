package com.yizhenwind.rocket.feature.categorysubject.ui

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.feature.categorysubject.databinding.ItemCategoryBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : BaseViewHolder<Category>(binding.root) {

    var onDeleteClickListener: ((Category) -> Unit)? = null

    override fun bind(item: Category) {
        binding.apply {
            item.apply {
                tvCategoryTitle.text = title
                tvCategoryRemark.text = remark
            }
            ibCategoryDelete.setThrottleClickListener { onDeleteClickListener?.invoke(item) }
            root.setThrottleClickListener { onItemClickListener?.invoke(item) }
        }
    }

}