package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.SubjectDto
import com.yizhenwind.rocket.core.model.Subject
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class SubjectDtoMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) : IMapper<SubjectDto, Subject> {

    override fun map(input: SubjectDto): Subject =
        input.run {
            Subject(
                subject.id,
                categoryMapper.fromEntity(category),
                subject.content,
                subject.createTime
            )
        }

}