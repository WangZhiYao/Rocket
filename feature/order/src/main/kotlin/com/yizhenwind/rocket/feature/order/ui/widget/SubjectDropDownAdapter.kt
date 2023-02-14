package com.yizhenwind.rocket.feature.order.ui.widget

import com.yizhenwind.rocket.core.framework.widget.BaseTwoLineTextDropDownAdapter
import com.yizhenwind.rocket.core.model.Subject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class SubjectDropDownAdapter : BaseTwoLineTextDropDownAdapter<Subject>() {

    override fun convertToString(item: Subject): CharSequence =
        item.content

    override fun contentBottom(item: Subject): CharSequence =
        item.category.title

}