package com.yizhenwind.rocket.feature.character.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/5
 */
data class CreateCharacterArgs(
    val clientId: Long = Constant.DEFAULT_ID,
    val accountId: Long = Constant.DEFAULT_ID
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateCharacterActivity::class.java).apply {
            putExtra(KEY_CLIENT_ID, clientId)
            putExtra(KEY_ACCOUNT_ID, accountId)
        }

    companion object : IActivityArgsDeserializer<CreateCharacterArgs> {

        private const val KEY_CLIENT_ID = "clientId"

        private const val KEY_ACCOUNT_ID = "accountId"

        @JvmStatic
        override fun deserialize(intent: Intent): CreateCharacterArgs =
            intent.run {
                CreateCharacterArgs(
                    getLongExtra(KEY_CLIENT_ID, Constant.DEFAULT_ID),
                    getLongExtra(KEY_ACCOUNT_ID, Constant.DEFAULT_ID)
                )
            }
    }
}