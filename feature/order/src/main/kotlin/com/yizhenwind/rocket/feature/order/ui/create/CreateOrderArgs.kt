package com.yizhenwind.rocket.feature.order.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/6
 */
data class CreateOrderArgs(val clientId: Long, val accountId: Long, val characterId: Long) :
    IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateOrderActivity::class.java).apply {
            putExtra(KEY_CLIENT_ID, clientId)
            putExtra(KEY_ACCOUNT_ID, accountId)
            putExtra(KEY_CHARACTER_ID, characterId)
        }

    companion object : IActivityArgsDeserializer<CreateOrderArgs> {

        private const val KEY_CLIENT_ID = "clientId"

        private const val KEY_ACCOUNT_ID = "accountId"

        private const val KEY_CHARACTER_ID = "characterId"

        @JvmStatic
        override fun deserialize(intent: Intent): CreateOrderArgs =
            intent.run {
                CreateOrderArgs(
                    getLongExtra(KEY_CLIENT_ID, Constant.DEFAULT_ID),
                    getLongExtra(KEY_ACCOUNT_ID, Constant.DEFAULT_ID),
                    getLongExtra(KEY_CHARACTER_ID, Constant.DEFAULT_ID)
                )
            }
    }
}