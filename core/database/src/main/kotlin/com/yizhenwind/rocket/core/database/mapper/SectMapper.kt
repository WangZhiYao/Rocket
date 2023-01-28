package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.SectEntity
import com.yizhenwind.rocket.core.model.Sect
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class SectMapper @Inject constructor() : IEntityMapper<SectEntity, Sect> {

    override fun fromEntity(entity: SectEntity): Sect =
        entity.run { Sect(id, name) }

    override fun toEntity(model: Sect): SectEntity =
        model.run { SectEntity(id, name) }

}