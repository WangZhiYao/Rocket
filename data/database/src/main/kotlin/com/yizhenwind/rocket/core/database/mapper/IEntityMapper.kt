package com.yizhenwind.rocket.core.database.mapper

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/21
 */
interface IEntityMapper<ENTITY, MODEL> {

    fun fromEntity(entity: ENTITY): MODEL

    fun toEntity(model: MODEL): ENTITY

}