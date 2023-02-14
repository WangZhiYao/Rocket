package com.yizhenwind.rocket.data.category.source

import com.yizhenwind.rocket.core.database.dao.CategoryDao
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class CategoryLocalDataSource @Inject constructor(
    private val categoryDao: CategoryDao
) {

    fun observeCategoryList(): Flow<List<CategoryEntity>> =
        categoryDao.observeCategoryList()

}