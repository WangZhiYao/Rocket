package com.yizhenwind.rocket.core.framework.widget

import android.content.Context
import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.model.Category

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class CategoryDropDownAdapter(private val context: Context) :
    BaseTwoLineTextDropDownAdapter<Category>() {

    override fun convertToString(item: Category): CharSequence =
        item.title

    override fun contentBottom(item: Category): CharSequence =
        item.remark.ifBlank { context.getString(R.string.empty_remark) }

}