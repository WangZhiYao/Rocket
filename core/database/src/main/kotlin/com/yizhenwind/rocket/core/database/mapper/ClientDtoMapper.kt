package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.model.Client
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
class ClientDtoMapper @Inject constructor(
    private val contactTypeMapper: ContactTypeMapper
) : IMapper<ClientDto, Client> {

    override fun map(input: ClientDto): Client =
        input.run {
            Client(
                client.id,
                client.name,
                contactTypeMapper.fromEntity(contactType),
                client.contact,
                client.remark,
                client.createTime
            )
        }

}