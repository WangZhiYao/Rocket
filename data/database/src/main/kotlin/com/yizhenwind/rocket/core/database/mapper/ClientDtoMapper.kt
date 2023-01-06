package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.dto.ClientDto
import com.yizhenwind.rocket.core.model.Client
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