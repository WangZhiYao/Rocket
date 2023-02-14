package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.simple.SimpleAccount

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class SimpleAccountDropDownAdapter : BaseOneLineTextDropDownAdapter<SimpleAccount>() {

    override fun convertToString(item: SimpleAccount): CharSequence = item.username

}