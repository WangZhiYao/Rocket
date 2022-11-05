package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface FragmentArgsDeserializer<T : FragmentArgs> {

    fun deserialize(arguments: Bundle): T

}