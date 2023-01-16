package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import com.yizhenwind.rocket.core.model.Client
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class ClientMapper @Inject constructor() : IMapper<Client, ClientEntity> {

    override fun map(input: Client): ClientEntity =
        input.run {
            ClientEntity(id, name, remark, createTime)
        }

}