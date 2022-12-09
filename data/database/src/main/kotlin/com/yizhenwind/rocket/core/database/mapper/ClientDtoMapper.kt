package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.model.Client
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.infra.mapper.IMapper
import com.yizhenwind.rocket.core.infra.mapper.ListMapper
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
class ClientDtoMapper @Inject constructor(
    private val contactMapper: ContactMapper
) : IMapper<ClientDto, Client> {

    override fun map(input: ClientDto): Client =
        input.run {
            Client(
                client.id,
                client.name,
                ListMapper(contactMapper).map(contactList),
                client.remark,
                client.createTime
            )
        }
}