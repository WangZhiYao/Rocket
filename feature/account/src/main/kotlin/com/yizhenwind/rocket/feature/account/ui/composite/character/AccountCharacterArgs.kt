package com.yizhenwind.rocket.feature.account.ui.composite.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.base.IFragmentArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountCharacterArgs(val accountId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment =
        AccountCharacterFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ACCOUNT_ID, accountId)
            }
        }

    companion object : IFragmentArgsDeserializer<AccountCharacterArgs> {

        private const val KEY_ACCOUNT_ID = "accountId"

        @JvmStatic
        override fun deserialize(arguments: Bundle): AccountCharacterArgs =
            arguments.run { AccountCharacterArgs(getLong(KEY_ACCOUNT_ID)) }

    }
}