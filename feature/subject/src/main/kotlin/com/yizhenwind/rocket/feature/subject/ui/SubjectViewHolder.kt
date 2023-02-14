package com.yizhenwind.rocket.feature.subject.ui

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.feature.subject.databinding.ItemSubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
class SubjectViewHolder(
    private val binding: ItemSubjectBinding
) : BaseViewHolder<Subject>(binding.root) {

    override fun bind(item: Subject) {
        binding.apply {
            item.apply {
                tvCategoryTitle.text = category.title
                tvSubjectContent.text = content
                root.setThrottleClickListener {
                    onItemClickListener?.invoke(this)
                }
            }
        }
    }

}