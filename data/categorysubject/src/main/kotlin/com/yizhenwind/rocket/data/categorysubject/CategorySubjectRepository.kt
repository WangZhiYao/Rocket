package com.yizhenwind.rocket.data.categorysubject

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.CategoryMapper
import com.yizhenwind.rocket.core.database.mapper.CategorySubjectDtoMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.core.model.CategorySubject
import com.yizhenwind.rocket.data.categorysubject.source.CategorySubjectLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
class CategorySubjectRepository @Inject constructor(
    private val categorySubjectLocalDataSource: CategorySubjectLocalDataSource,
    private val categoryMapper: CategoryMapper,
    private val categorySubjectDtoMapper: CategorySubjectDtoMapper
) {

    fun observeCategoryList(): Flow<List<Category>> =
        categorySubjectLocalDataSource.observeCategoryList()
            .map { EntityListMapper(categoryMapper).fromEntity(it) }

    fun observeCategorySubjectList(): Flow<List<CategorySubject>> =
        categorySubjectLocalDataSource.observeCategorySubjectList()
            .map { ListMapper(categorySubjectDtoMapper).map(it) }

    fun deleteCategory(category: Category): Flow<Int> =
        categorySubjectLocalDataSource.deleteCategory(categoryMapper.toEntity(category))

}