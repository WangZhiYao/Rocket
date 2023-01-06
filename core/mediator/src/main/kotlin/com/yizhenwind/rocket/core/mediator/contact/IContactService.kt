package com.yizhenwind.rocket.core.mediator.contact

import com.yizhenwind.rocket.core.common.constant.ContactType
import com.yizhenwind.rocket.core.model.Contact
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
interface IContactService {

    /**
     * 创建联系方式
     *
     * @param clientId    客户ID
     * @param contactType 联系方式类型
     * @param value       联系方式
     */
    fun createContact(clientId: Long, contactType: ContactType, value: String): Flow<Contact>

    /**
     * 获取联系方式
     *
     * @param contactType 联系方式类型
     * @param value       联系方式
     */
    fun getContact(contactType: ContactType, value: String): Flow<Contact>
}