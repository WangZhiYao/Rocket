package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.simple.SimpleClient

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class SimpleClientDropDownAdapter : BaseTwoLineTextDropDownAdapter<SimpleClient>() {

    override fun convertToString(item: SimpleClient): CharSequence = item.name

    override fun contentBottom(item: SimpleClient): CharSequence =
        item.run { String.format("%s: %s", contactType.name, contact) }

}