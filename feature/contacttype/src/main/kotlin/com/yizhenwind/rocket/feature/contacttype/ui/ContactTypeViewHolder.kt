package com.yizhenwind.rocket.feature.contacttype.ui

import androidx.core.view.isVisible
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.feature.contacttype.databinding.ItemContactTypeBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class ContactTypeViewHolder(private val binding: ItemContactTypeBinding) :
    BaseViewHolder<ContactType>(binding.root) {

    var onDeleteClickListener: ((ContactType) -> Unit)? = null

    override fun bind(item: ContactType) {
        binding.apply {
            tvContactTypeName.text = item.name
            ibContactTypeDelete.apply {
                isVisible = !item.default
                setThrottleClickListener {
                    onDeleteClickListener?.invoke(item)
                }
            }
        }
    }
}