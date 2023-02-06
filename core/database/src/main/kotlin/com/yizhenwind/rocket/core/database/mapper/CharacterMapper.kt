package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.CharacterEntity
import com.yizhenwind.rocket.core.model.Character
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/5
 */
class CharacterMapper @Inject constructor() : IMapper<Character, CharacterEntity> {

    override fun map(input: Character): CharacterEntity =
        input.run {
            CharacterEntity(
                id,
                client.id,
                zone.id,
                server.id,
                account.id,
                name,
                sect.id,
                internal.id,
                securityLock,
                remark,
                enable,
                createTime
            )
        }

}