package com.yizhenwind.rocket.domain.category

import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.data.category.CategoryRepository
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
class GetCategoryByTitleUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(title: String): Category =
        categoryRepository.getCategoryByTitle(title) ?: Category()

}