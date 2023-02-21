package com.yizhenwind.rocket.feature.client.ui.create

import com.yizhenwind.rocket.core.framework.widget.BaseOneLineTextDropDownAdapter
import com.yizhenwind.rocket.core.model.ContactType

/**
 *
 * @author WangZhiYao
 * @since 2023/2/21
 */
class ContactTypeAdapter : BaseOneLineTextDropDownAdapter<ContactType>() {

    override fun convertToString(item: ContactType): CharSequence = item.name

}