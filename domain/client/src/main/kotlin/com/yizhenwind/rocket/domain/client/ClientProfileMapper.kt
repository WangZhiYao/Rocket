package com.yizhenwind.rocket.domain.client

import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.infra.mapper.IMapper
import com.yizhenwind.rocket.data.model.ClientProfile
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientProfileMapper @Inject constructor() : IMapper<ClientProfileDto, ClientProfile> {

    override fun map(input: ClientProfileDto): ClientProfile =
        input.run {
            ClientProfile(
                id,
                name,
                contactType,
                contact,
                accountCount,
                characterCount,
                orderCount,
                createTime
            )
        }

}