package com.yizhenwind.rocket.data.category.source

import com.yizhenwind.rocket.core.database.dao.CategoryDao
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    suspend fun getCategoryByTitle(title: String): CategoryEntity? =
        categoryDao.getCategoryByTitle(title)

    fun createCategory(categoryEntity: CategoryEntity): Flow<Long> =
        flow {
            emit(categoryDao.insert(categoryEntity))
        }

    fun deleteCategory(categoryEntity: CategoryEntity): Flow<Int> =
        flow {
            emit(categoryDao.delete(categoryEntity))
        }

}