package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.Internal

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class InternalDropDownAdapter : BaseOneLineTextDropDownAdapter<Internal>() {

    override fun itemToString(item: Internal): CharSequence = item.name

}