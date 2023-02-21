package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.ClientTupleDto
import com.yizhenwind.rocket.core.model.ClientTuple
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class ClientTupleMapper @Inject constructor(
    private val contactTypeMapper: ContactTypeMapper
) : IMapper<ClientTupleDto, ClientTuple> {

    override fun map(input: ClientTupleDto): ClientTuple =
        input.run {
            ClientTuple(
                id,
                name,
                contactTypeMapper.fromEntity(contactTypeEntity),
                contact
            )
        }

}