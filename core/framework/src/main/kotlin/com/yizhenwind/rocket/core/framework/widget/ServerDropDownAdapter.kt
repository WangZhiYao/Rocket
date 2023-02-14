package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.Server

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class ServerDropDownAdapter : BaseOneLineTextDropDownAdapter<Server>() {

    override fun convertToString(item: Server): CharSequence = item.name

}