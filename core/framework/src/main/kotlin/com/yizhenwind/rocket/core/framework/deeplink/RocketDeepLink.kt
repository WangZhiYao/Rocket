package com.yizhenwind.rocket.core.framework.deeplink

import com.airbnb.deeplinkdispatch.DeepLinkSpec

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
@DeepLinkSpec(prefix = ["rocket://"])
@Retention(AnnotationRetention.RUNTIME)
annotation class RocketDeepLink(val value: Array<String>)