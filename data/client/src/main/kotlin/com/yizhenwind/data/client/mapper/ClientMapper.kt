package com.yizhenwind.data.client.mapper

import com.yizhenwind.rocket.core.database.entity.ClientEntity
import com.yizhenwind.rocket.core.database.mapper.IEntityMapper
import com.yizhenwind.rocket.core.model.Client
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class ClientMapper @Inject constructor() : IEntityMapper<ClientEntity, Client> {

    override fun fromEntity(entity: ClientEntity): Client =
        entity.run {
            Client(
                id,
                name,
                contactType,
                contact,
                remark,
                createTime
            )
        }

    override fun toEntity(model: Client): ClientEntity =
        model.run {
            ClientEntity(
                id,
                name,
                contactType,
                contact,
                remark,
                createTime
            )
        }
}