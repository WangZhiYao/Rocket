package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.yizhenwind.rocket.core.framework.databinding.ItemAccountProfileBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.AccountProfile

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class AccountProfileAdapter :
    BaseListAdapter<AccountProfile, AccountProfileViewHolder>(ACCOUNT_PROFILE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountProfileViewHolder =
        AccountProfileViewHolder(parent.viewBinding(ItemAccountProfileBinding::inflate)).apply {
            onItemClickListener = { accountProfile ->
                this@AccountProfileAdapter.onItemClickListener?.invoke(accountProfile)
            }
        }

    companion object {

        private val ACCOUNT_PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<AccountProfile>() {

            override fun areItemsTheSame(
                oldItem: AccountProfile,
                newItem: AccountProfile
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: AccountProfile,
                newItem: AccountProfile
            ): Boolean =
                oldItem == newItem

        }
    }
}