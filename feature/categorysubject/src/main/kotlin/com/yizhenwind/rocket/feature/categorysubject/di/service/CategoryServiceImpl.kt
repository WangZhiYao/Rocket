package com.yizhenwind.rocket.feature.categorysubject.di.service

import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.domain.category.ObserveCategoryListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
class CategoryServiceImpl @Inject constructor(
    private val observeCategoryListUseCase: ObserveCategoryListUseCase
) : ICategoryService {

    override fun observeCategoryList(): Flow<List<Category>> =
        observeCategoryListUseCase()

}