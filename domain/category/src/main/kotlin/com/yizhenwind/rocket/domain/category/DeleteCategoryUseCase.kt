package com.yizhenwind.rocket.domain.category

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.data.category.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(category: Category): Flow<Int> =
        categoryRepository.deleteCategory(category).flowOn(dispatcher)

}