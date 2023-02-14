package com.yizhenwind.rocket.data.category

import com.yizhenwind.rocket.core.database.mapper.CategoryMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.data.category.source.CategoryLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class CategoryRepository @Inject constructor(
    private val categoryLocalDataSource: CategoryLocalDataSource,
    private val categoryMapper: CategoryMapper
) {

    fun observeCategoryList(): Flow<List<Category>> =
        categoryLocalDataSource.observeCategoryList()
            .map { EntityListMapper(categoryMapper).fromEntity(it) }

}