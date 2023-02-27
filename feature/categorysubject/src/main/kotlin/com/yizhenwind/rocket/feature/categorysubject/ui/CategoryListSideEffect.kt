package com.yizhenwind.rocket.feature.categorysubject.ui

import androidx.annotation.StringRes

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
sealed class CategoryListSideEffect {

    object DeleteCategorySuccess : CategoryListSideEffect()

    data class ShowError(@StringRes val resId: Int) : CategoryListSideEffect()

}