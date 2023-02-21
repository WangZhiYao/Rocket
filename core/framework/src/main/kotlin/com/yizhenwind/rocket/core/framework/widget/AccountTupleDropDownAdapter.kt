package com.yizhenwind.rocket.core.framework.widget

import com.yizhenwind.rocket.core.model.AccountTuple

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class AccountTupleDropDownAdapter : BaseOneLineTextDropDownAdapter<AccountTuple>() {

    override fun convertToString(item: AccountTuple): CharSequence = item.username

}