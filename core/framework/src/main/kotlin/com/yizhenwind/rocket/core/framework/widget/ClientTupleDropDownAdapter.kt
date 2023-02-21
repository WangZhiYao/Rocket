package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.ClientTuple

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class ClientTupleDropDownAdapter : BaseTwoLineTextDropDownAdapter<ClientTuple>() {

    override fun convertToString(item: ClientTuple): CharSequence = item.name

    override fun contentBottom(item: ClientTuple): CharSequence =
        item.run { String.format("%s: %s", contactType.name, contact) }

}