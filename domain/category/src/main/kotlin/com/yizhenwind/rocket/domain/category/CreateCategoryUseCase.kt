package com.yizhenwind.rocket.domain.category

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.data.category.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
class CreateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(category: Category): Flow<Category> =
        categoryRepository.createCategory(category)
            .map { id -> category.copy(id = id) }
            .flowOn(dispatcher)

}