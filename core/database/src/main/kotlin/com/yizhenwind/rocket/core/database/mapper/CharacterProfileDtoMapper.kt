package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import com.yizhenwind.rocket.core.model.CharacterProfile
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
class CharacterProfileDtoMapper @Inject constructor(
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper,
    private val sectMapper: SectMapper
) : IMapper<CharacterProfileDto, CharacterProfile> {

    override fun map(input: CharacterProfileDto): CharacterProfile =
        input.run {
            CharacterProfile(
                id,
                zoneMapper.fromEntity(zone),
                serverMapper.fromEntity(server),
                name,
                sectMapper.fromEntity(sect),
                remark,
                createTime
            )
        }
}