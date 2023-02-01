package com.yizhenwind.rocket.core.authenticate.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/1
 */
data class CipherData(
    val ciphertext: String,
    val iv: String
)
