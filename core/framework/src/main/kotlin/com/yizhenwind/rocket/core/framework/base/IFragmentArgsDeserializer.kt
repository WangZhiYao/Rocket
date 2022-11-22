package com.yizhenwind.rocket.core.framework.base

import android.os.Bundle

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IFragmentArgsDeserializer<T : IFragmentArgs> {

    fun deserialize(arguments: Bundle): T

}