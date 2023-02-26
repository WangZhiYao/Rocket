package com.yizhenwind.rocket.feature.category.ui

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
sealed class CategoryListSideEffect {

    object DeleteCategorySuccess : CategoryListSideEffect()

}