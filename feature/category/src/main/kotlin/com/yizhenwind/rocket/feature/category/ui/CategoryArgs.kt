package com.yizhenwind.rocket.feature.category.ui

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.framework.base.IActivityArgs
import com.yizhenwind.rocket.core.framework.base.IActivityArgsDeserializer

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
class CategoryArgs : IActivityArgs {

    override fun intent(context: Context): Intent =
        Intent(context, CategoryNavActivity::class.java)

    companion object : IActivityArgsDeserializer<CategoryArgs> {

        @JvmStatic
        override fun deserialize(intent: Intent): CategoryArgs =
            intent.run { CategoryArgs() }

    }

}