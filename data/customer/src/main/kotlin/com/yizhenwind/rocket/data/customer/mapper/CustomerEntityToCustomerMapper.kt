package com.yizhenwind.rocket.data.customer.mapper

import com.yizhenwind.rocket.core.database.entity.CustomerEntity
import com.yizhenwind.rocket.core.infra.mapper.IMapper
import com.yizhenwind.rocket.data.customer.model.Customer
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2022/3/25
 */
class CustomerEntityToCustomerMapper @Inject constructor() : IMapper<CustomerEntity, Customer> {

    override fun map(input: CustomerEntity) =
        input.run {
            Customer(
                id,
                name,
                contactType,
                contact,
                remark,
                createTime
            )
        }

}