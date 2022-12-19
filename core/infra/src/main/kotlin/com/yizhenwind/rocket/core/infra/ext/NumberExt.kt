package com.yizhenwind.rocket.core.infra.ext

import android.content.res.Resources
import android.util.TypedValue
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