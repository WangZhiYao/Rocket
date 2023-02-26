package com.yizhenwind.rocket.core.framework.ui

import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemAccountBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding
import com.yizhenwind.rocket.core.model.Account

/**
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
class AccountAdapter :
    BaseListAdapter<Account, AccountViewHolder>(Account.COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder =
        AccountViewHolder(parent.viewBinding(ItemAccountBinding::inflate)).apply {
            onItemClickListener = { accountProfile ->
                this@AccountAdapter.onItemClickListener?.invoke(accountProfile)
            }
        }

}