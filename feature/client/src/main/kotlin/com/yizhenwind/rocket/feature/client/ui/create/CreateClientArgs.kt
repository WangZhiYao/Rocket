package com.yizhenwind.rocket.feature.client.ui.create

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.framework.base.IActivityArgs

/**
 *
 * @author WangZhiYao
 * @since 2023/1/17
 */
class CreateClientArgs : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CreateClientActivity::class.java)

}