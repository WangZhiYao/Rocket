package com.yizhenwind.rocket.feature.contacttype.ui

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
class ContactTypeArgs : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, ContactTypeNavActivity::class.java)

    companion object : IActivityArgsDeserializer<ContactTypeArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): ContactTypeArgs =
            intent.run { ContactTypeArgs() }

    }
}