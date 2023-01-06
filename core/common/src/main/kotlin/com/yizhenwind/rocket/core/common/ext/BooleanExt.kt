package com.yizhenwind.rocket.core.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/12
 */
fun Boolean.ifTrue(block: () -> Unit): Boolean {
    return this.also { if (this) block() }
}