package com.yizhenwind.rocket.feature.client.ui.create

import android.content.Context
import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.framework.widget.BaseOneLineTextDropDownAdapter

/**
 *
 * @author WangZhiYao
 * @since 2023/2/21
 */
class ContactTypeAdapter(
    private val context: Context
) : BaseOneLineTextDropDownAdapter<ContactType>() {

    override fun itemToString(item: ContactType): CharSequence = context.getString(item.resId)

}