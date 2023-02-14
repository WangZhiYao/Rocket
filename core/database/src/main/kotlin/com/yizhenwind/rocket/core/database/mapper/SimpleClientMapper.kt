package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.simple.SimpleClientDto
import com.yizhenwind.rocket.core.model.simple.SimpleClient
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class SimpleClientMapper @Inject constructor(
    private val contactTypeMapper: ContactTypeMapper
) : IMapper<SimpleClientDto, SimpleClient> {

    override fun map(input: SimpleClientDto): SimpleClient =
        input.run {
            SimpleClient(
                id,
                name,
                contactTypeMapper.fromEntity(contactTypeEntity),
                contact
            )
        }

}