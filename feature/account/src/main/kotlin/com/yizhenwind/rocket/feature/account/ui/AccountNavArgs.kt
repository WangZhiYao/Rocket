package com.yizhenwind.rocket.feature.account.ui

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
data class AccountNavArgs(val clientId: Long) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, AccountNavActivity::class.java).apply {
            putExtra(KEY_CLIENT_ID, clientId)
        }

    companion object : IActivityArgsDeserializer<AccountNavArgs> {

        private const val KEY_CLIENT_ID = "clientId"

        @JvmStatic
        override fun deserialize(intent: Intent): AccountNavArgs =
            intent.run { AccountNavArgs(getLongExtra(KEY_CLIENT_ID, Constant.DEFAULT_ID)) }

    }
}