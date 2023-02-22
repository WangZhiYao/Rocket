package com.yizhenwind.rocket.feature.category.ui

import com.yizhenwind.rocket.core.model.Category

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
sealed class CategoryListSideEffect {

    data class DeleteCategorySuccess(val category: Category) : CategoryListSideEffect()

}