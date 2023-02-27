package com.yizhenwind.rocket.data.categorysubject.source

import com.yizhenwind.rocket.core.database.dao.CategoryDao
import com.yizhenwind.rocket.core.database.dto.CategorySubjectDto
import com.yizhenwind.rocket.core.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
class CategorySubjectLocalDataSource @Inject constructor(
    private val categoryDao: CategoryDao
) {

    fun observeCategoryList(): Flow<List<CategoryEntity>> =
        categoryDao.observeCategoryList()

    fun observeCategorySubjectList(): Flow<List<CategorySubjectDto>> =
        categoryDao.observeCategorySubjectList()

    fun deleteCategory(categoryEntity: CategoryEntity): Flow<Int> =
        flow {
            emit(categoryDao.delete(categoryEntity))
        }

}