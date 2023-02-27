package com.yizhenwind.rocket.feature.categorysubject.ui.subject

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.feature.categorysubject.databinding.ItemSubjectBinding

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
class SubjectAdapter : BaseListAdapter<Subject, SubjectViewHolder>(Subject.COMPARATOR) {

    var onDeleteClickListener: ((Subject) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(parent.viewBinding(ItemSubjectBinding::inflate)).apply {
            onDeleteClickListener = { subject ->
                this@SubjectAdapter.onDeleteClickListener?.invoke(subject)
            }
        }
}