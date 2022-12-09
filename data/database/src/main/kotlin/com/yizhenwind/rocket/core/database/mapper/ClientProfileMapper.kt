package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.dto.ClientProfileDto
import com.yizhenwind.rocket.core.infra.mapper.IMapper
import com.yizhenwind.rocket.core.common.model.ClientProfile
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
                accountCount,
                characterCount,
                orderCount,
                createTime
            )
        }

}