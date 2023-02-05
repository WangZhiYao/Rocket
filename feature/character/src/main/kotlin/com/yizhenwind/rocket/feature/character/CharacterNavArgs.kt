package com.yizhenwind.rocket.feature.character

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer
import com.yizhenwind.rocket.feature.character.ui.CharacterNavActivity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/5
 */
data class CharacterNavArgs(
    val clientId: Long = Constant.DEFAULT_ID,
    val accountId: Long = Constant.DEFAULT_ID
) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CharacterNavActivity::class.java).apply {
            putExtra(KEY_CLIENT_ID, clientId)
            putExtra(KEY_ACCOUNT_ID, accountId)
        }

    companion object : IActivityArgsDeserializer<CharacterNavArgs> {

        private const val KEY_CLIENT_ID = "clientId"

        private const val KEY_ACCOUNT_ID = "accountId"

        @JvmStatic
        override fun deserialize(intent: Intent): CharacterNavArgs =
            intent.run {
                CharacterNavArgs(
                    getLongExtra(KEY_CLIENT_ID, Constant.DEFAULT_ID),
                    getLongExtra(KEY_ACCOUNT_ID, Constant.DEFAULT_ID)
                )
            }
    }
}