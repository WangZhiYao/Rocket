package com.yizhenwind.rocket.feature.account.ui.composite.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.base.IFragmentArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountDetailArgs(val accountId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment =
        AccountDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ACCOUNT_ID, accountId)
            }
        }

    companion object : IFragmentArgsDeserializer<AccountDetailArgs> {

        private const val KEY_ACCOUNT_ID = "accountId"

        @JvmStatic
        override fun deserialize(arguments: Bundle): AccountDetailArgs =
            arguments.run { AccountDetailArgs(getLong(KEY_ACCOUNT_ID)) }

    }

}