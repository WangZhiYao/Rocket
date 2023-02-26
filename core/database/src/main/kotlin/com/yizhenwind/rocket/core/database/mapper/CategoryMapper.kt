package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import com.yizhenwind.rocket.core.model.Category
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class CategoryMapper @Inject constructor() : IEntityMapper<CategoryEntity, Category> {

    override fun fromEntity(entity: CategoryEntity): Category =
        entity.run { Category(id, title, remark, createTime) }

    override fun toEntity(model: Category): CategoryEntity =
        model.run { CategoryEntity(id, title, remark, createTime) }

}