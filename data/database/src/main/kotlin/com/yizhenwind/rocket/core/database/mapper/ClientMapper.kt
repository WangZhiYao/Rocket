package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import com.yizhenwind.rocket.core.infra.mapper.IMapper
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