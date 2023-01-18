package com.yizhenwind.data.client.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.model.ClientProfile
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
class ClientProfileDtoMapper @Inject constructor() : IMapper<ClientProfileDto, ClientProfile> {

    override fun map(input: ClientProfileDto): ClientProfile =
        input.run {
            ClientProfile(
                id,
                name,
                accountCount,
                characterCount,
                orderCount,
                createTime
            )
        }

}