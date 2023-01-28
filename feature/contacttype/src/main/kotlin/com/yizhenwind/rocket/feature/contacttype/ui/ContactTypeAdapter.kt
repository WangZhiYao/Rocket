package com.yizhenwind.rocket.feature.contacttype.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.base.BaseListAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.ContactType
import com.yizhenwind.rocket.feature.contacttype.databinding.ItemContactTypeBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class ContactTypeAdapter :
    BaseListAdapter<ContactType, ContactTypeViewHolder>(CONTACT_TYPE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactTypeViewHolder =
        ContactTypeViewHolder(parent.viewBinding(ItemContactTypeBinding::inflate)).apply {
            onItemClickListener = { contactType ->
                this@ContactTypeAdapter.onItemClickListener?.invoke(contactType)
            }
        }

    companion object {

        private val CONTACT_TYPE_COMPARATOR = object : DiffUtil.ItemCallback<ContactType>() {

            override fun areItemsTheSame(oldItem: ContactType, newItem: ContactType): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ContactType, newItem: ContactType): Boolean =
                oldItem == newItem

        }
    }
}