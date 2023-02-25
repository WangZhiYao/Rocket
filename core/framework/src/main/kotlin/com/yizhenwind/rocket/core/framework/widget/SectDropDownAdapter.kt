package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.Sect

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class SectDropDownAdapter : BaseOneLineTextDropDownAdapter<Sect>() {

    override fun itemToString(item: Sect): CharSequence = item.name

}