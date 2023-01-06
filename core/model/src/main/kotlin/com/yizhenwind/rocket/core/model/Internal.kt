package com.yizhenwind.rocket.core.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * 心法
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Internal(
    val id: Long = 0,
    val sectId: Long = 0,
    val name: String = "",
    val icon: String = ""
) : Parcelable
