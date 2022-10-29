package com.yizhenwind.rocket.data.customer.model

import android.os.Parcelable
import com.yizhenwind.rocket.core.common.constant.ContactType
import kotlinx.parcelize.Parcelize

/**
 * 客户
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */

@Parcelize
data class Customer(
    val id: Long = 0,
    val name: String = "",
    var contactType: ContactType = ContactType.QQ,
    var contact: String = "",
    var remark: String? = null,
    val createTime: Long = System.currentTimeMillis()
) : Parcelable