package com.yizhenwind.rocket.feature.client.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
data class ClientNavArgs(val navUri: Uri? = null) : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, ClientNavActivity::class.java).apply {
            navUri?.apply { putExtra(KEY_CLIENT_NAV_URI, this) }
        }

    companion object : IActivityArgsDeserializer<ClientNavArgs> {

        private const val KEY_CLIENT_NAV_URI = "CLIENT_NAV_URI"

        @JvmStatic
        override fun deserialize(intent: Intent): ClientNavArgs =
            intent.run {
                ClientNavArgs(getParcelableExtra(KEY_CLIENT_NAV_URI))
            }
    }
}