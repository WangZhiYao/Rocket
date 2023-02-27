package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.SubjectEntity
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.core.model.Subject
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
class SubjectMapper @Inject constructor() : IEntityMapper<SubjectEntity, Subject> {

    override fun fromEntity(entity: SubjectEntity): Subject =
        entity.run { Subject(id, Category(id = categoryId), content, createTime) }

    override fun toEntity(model: Subject): SubjectEntity =
        model.run { SubjectEntity(id, category.id, content, createTime) }
}