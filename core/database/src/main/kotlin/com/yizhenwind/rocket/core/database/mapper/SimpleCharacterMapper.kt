package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.simple.SimpleCharacterDto
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class SimpleCharacterMapper @Inject constructor(
    private val simpleAccountMapper: SimpleAccountMapper,
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper,
    private val sectMapper: SectMapper
) : IMapper<SimpleCharacterDto, SimpleCharacter> {

    override fun map(input: SimpleCharacterDto): SimpleCharacter =
        input.run {
            SimpleCharacter(
                id,
                simpleAccountMapper.map(simpleAccountDto),
                zoneMapper.fromEntity(zoneEntity),
                serverMapper.fromEntity(serverEntity),
                name,
                sectMapper.fromEntity(sectEntity)
            )
        }

}