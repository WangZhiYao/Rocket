package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import com.yizhenwind.rocket.core.database.entity.SubjectEntity

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
data class SubjectDto(
    @Embedded
    val subject: SubjectEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id",
    )
    val category: CategoryEntity
)