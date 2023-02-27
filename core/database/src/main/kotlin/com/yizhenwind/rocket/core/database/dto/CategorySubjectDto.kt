package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import com.yizhenwind.rocket.core.database.entity.SubjectEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
data class CategorySubjectDto(
    @Embedded
    val categoryEntity: CategoryEntity,
    @Relation(
        entity = SubjectEntity::class,
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val subjectDtoList: List<SubjectDto>,
)