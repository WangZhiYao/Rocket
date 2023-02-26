package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.SubjectEntity
import com.yizhenwind.rocket.core.model.Subject
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
class SubjectMapper @Inject constructor() : IMapper<Subject, SubjectEntity> {

    override fun map(input: Subject): SubjectEntity =
        input.run { SubjectEntity(id, category.id, content, createTime) }

}