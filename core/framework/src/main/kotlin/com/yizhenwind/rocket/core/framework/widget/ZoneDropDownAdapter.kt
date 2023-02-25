package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.Zone

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class ZoneDropDownAdapter : BaseOneLineTextDropDownAdapter<Zone>() {

    override fun itemToString(item: Zone): CharSequence = item.name

}