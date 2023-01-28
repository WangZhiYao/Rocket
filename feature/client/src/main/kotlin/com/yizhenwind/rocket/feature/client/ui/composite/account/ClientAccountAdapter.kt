package com.yizhenwind.rocket.feature.client.ui.composite.account

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yizhenwind.rocket.core.framework.base.BaseListAdapter
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.feature.client.databinding.ItemClientAccountBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class ClientAccountAdapter :
    BaseListAdapter<AccountProfile, ClientAccountViewHolder>(ACCOUNT_PROFILE_COMPARATOR) {

    var onActionClickListener: ((AccountProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientAccountViewHolder =
        ClientAccountViewHolder(parent.viewBinding(ItemClientAccountBinding::inflate)).apply {
            onItemClickListener = { accountProfile ->
                this@ClientAccountAdapter.onItemClickListener?.invoke(accountProfile)
            }
            onActionClickListener = { accountProfile ->
                this@ClientAccountAdapter.onActionClickListener?.invoke(accountProfile)
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