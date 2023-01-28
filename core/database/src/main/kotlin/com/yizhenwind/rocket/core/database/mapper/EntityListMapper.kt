package com.yizhenwind.rocket.core.database.mapper

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class EntityListMapper<ENTITY, MODEL>(private val entityMapper: IEntityMapper<ENTITY, MODEL>) :
    IEntityMapper<List<ENTITY>, List<MODEL>> {

    override fun fromEntity(entity: List<ENTITY>): List<MODEL> =
        entity.map { entityMapper.fromEntity(it) }

    override fun toEntity(model: List<MODEL>): List<ENTITY> =
        model.map { entityMapper.toEntity(it) }

}