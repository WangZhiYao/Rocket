package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.model.Character
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterDtoMapper @Inject constructor(
    private val clientDtoMapper: ClientDtoMapper,
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper,
    private val accountMapper: AccountMapper,
    private val sectMapper: SectMapper,
    private val internalMapper: InternalMapper
) : IMapper<CharacterDto, Character> {

    override fun map(input: CharacterDto): Character =
        input.run {
            Character(
                character.id,
                clientDtoMapper.map(client),
                zoneMapper.fromEntity(zone),
                serverMapper.fromEntity(server),
                accountMapper.fromEntity(account),
                character.securityLock,
                character.name,
                sectMapper.fromEntity(sect),
                internalMapper.fromEntity(internal),
                character.remark,
                character.enable,
                character.createTime
            )
        }
}