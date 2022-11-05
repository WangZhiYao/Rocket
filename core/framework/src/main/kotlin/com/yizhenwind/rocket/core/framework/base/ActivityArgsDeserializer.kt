package com.yizhenwind.rocket.core.framework.base

import android.content.Intent

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface ActivityArgsDeserializer<T : ActivityArgs> {

    fun deserialize(intent: Intent): T

}