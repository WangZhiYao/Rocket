package com.yizhenwind.rocket.core.common.ext

import java.math.BigDecimal

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
private val AMOUNT_UNIT = BigDecimal(100)

fun String.hexStringToByteArray(): ByteArray =
    chunked(2).map { it.uppercase().toInt(16).toByte() }.toByteArray()

fun String?.toAmount(): Long =
    if (isNullOrBlank()) {
        0
    } else {
        try {
            BigDecimal(this).multiply(AMOUNT_UNIT).longValueExact()
        } catch (ex: NumberFormatException) {
            ex.printStackTrace()
            0
        }
    }
