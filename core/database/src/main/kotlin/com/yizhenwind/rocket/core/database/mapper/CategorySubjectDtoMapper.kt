package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.dto.CategorySubjectDto
import com.yizhenwind.rocket.core.model.CategorySubject
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
class CategorySubjectDtoMapper @Inject constructor(
    private val categoryMapper: CategoryMapper,
    private val subjectDtoMapper: SubjectDtoMapper
) : IMapper<CategorySubjectDto, CategorySubject> {

    override fun map(input: CategorySubjectDto): CategorySubject =
        input.run {
            CategorySubject(
                categoryMapper.fromEntity(categoryEntity),
                ListMapper(subjectDtoMapper).map(subjectDtoList)
            )
        }

}