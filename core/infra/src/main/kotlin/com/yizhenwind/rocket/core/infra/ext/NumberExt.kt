package com.yizhenwind.rocket.core.infra.ext

import android.content.res.Resources
import android.util.TypedValue
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/17
 */
val Number.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()

val Number.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()

fun Long.formatDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))