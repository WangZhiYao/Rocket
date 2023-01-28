package com.yizhenwind.rocket.core.framework.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
object ClipboardHelper {

    fun copyTo(context: Context, value: String): Boolean {
        return try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(value, value)
            clipboard.setPrimaryClip(clipData)
            true
        } catch (ex: Exception) {
            false
        }
    }

}