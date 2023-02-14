package com.yizhenwind.rocket.feature.subject.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.framework.ui.BaseListAdapter
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.feature.subject.databinding.ItemSubjectBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
class SubjectAdapter : BaseListAdapter<Subject, SubjectViewHolder>(SUBJECT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(parent.viewBinding(ItemSubjectBinding::inflate))

    companion object {

        private val SUBJECT_COMPARATOR =
            object : DiffUtil.ItemCallback<Subject>() {

                override fun areItemsTheSame(
                    oldItem: Subject,
                    newItem: Subject
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Subject,
                    newItem: Subject
                ): Boolean =
                    oldItem == newItem

            }

    }
}