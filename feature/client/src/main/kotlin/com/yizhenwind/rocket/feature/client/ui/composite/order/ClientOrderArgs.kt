package com.yizhenwind.rocket.feature.client.ui.composite.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yizhenwind.rocket.core.framework.base.IFragmentArgs
import com.yizhenwind.rocket.core.framework.base.IFragmentArgsDeserializer

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
data class ClientOrderArgs(val clientId: Long) : IFragmentArgs {

    override fun newInstance(): Fragment =
        ClientOrderFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_CLIENT_ID, clientId)
            }
        }

    companion object : IFragmentArgsDeserializer<ClientOrderArgs> {

        private const val KEY_CLIENT_ID = "CLIENT_ID"

        @JvmStatic
        override fun deserialize(arguments: Bundle): ClientOrderArgs =
            arguments.run { ClientOrderArgs(getLong(KEY_CLIENT_ID)) }

    }
}