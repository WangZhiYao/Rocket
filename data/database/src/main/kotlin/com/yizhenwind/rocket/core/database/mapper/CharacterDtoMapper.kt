package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.model.Character
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class CharacterDtoMapper @Inject constructor(
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper,
    private val sectMapper: SectMapper,
    private val internalMapper: InternalMapper
) : IMapper<CharacterDto, Character> {

    override fun map(input: CharacterDto): Character =
        input.run {
            Character(
                character.id,
                character.clientId,
                zoneMapper.fromEntity(zoneEntity),
                serverMapper.fromEntity(serverEntity),
                character.account,
                character.password,
                character.securityLock,
                character.name,
                sectMapper.fromEntity(sectEntity),
                internalMapper.fromEntity(internalEntity),
                character.remark,
                character.createTime
            )
        }
}