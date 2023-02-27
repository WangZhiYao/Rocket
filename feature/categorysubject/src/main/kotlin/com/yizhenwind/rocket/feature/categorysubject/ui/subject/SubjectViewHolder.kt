package com.yizhenwind.rocket.feature.categorysubject.ui.subject

import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ui.BaseViewHolder
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.feature.categorysubject.databinding.ItemSubjectBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
class SubjectViewHolder(
    private val binding: ItemSubjectBinding
) : BaseViewHolder<Subject>(binding.root) {

    var onDeleteClickListener: ((Subject) -> Unit)? = null

    override fun bind(item: Subject) {
        binding.apply {
            item.apply {
                tvSubjectContent.text = content
                ibSubjectDelete.setThrottleClickListener {
                    onDeleteClickListener?.invoke(this)
                }
            }
        }
    }

}