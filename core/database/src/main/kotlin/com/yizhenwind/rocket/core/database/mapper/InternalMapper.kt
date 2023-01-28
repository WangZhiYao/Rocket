package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.InternalEntity
import com.yizhenwind.rocket.core.model.Internal
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class InternalMapper @Inject constructor() : IEntityMapper<InternalEntity, Internal> {

    override fun fromEntity(entity: InternalEntity): Internal =
        entity.run { Internal(id, sectId, name) }

    override fun toEntity(model: Internal): InternalEntity =
        model.run { InternalEntity(id, sectId, name) }

}