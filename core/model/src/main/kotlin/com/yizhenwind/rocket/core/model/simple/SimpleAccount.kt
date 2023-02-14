package com.yizhenwind.rocket.core.model.simple

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class SimpleAccount(
    val id: Long = 0,
    val username: String = ""
) {

    override fun toString() = username

}