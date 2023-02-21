package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.CharacterTupleDto
import com.yizhenwind.rocket.core.model.CharacterTuple
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class CharacterTupleMapper @Inject constructor(
    private val accountTupleMapper: AccountTupleMapper,
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper,
    private val sectMapper: SectMapper
) : IMapper<CharacterTupleDto, CharacterTuple> {

    override fun map(input: CharacterTupleDto): CharacterTuple =
        input.run {
            CharacterTuple(
                id,
                accountTupleMapper.map(accountTuple),
                zoneMapper.fromEntity(zoneEntity),
                serverMapper.fromEntity(serverEntity),
                name,
                sectMapper.fromEntity(sectEntity)
            )
        }

}