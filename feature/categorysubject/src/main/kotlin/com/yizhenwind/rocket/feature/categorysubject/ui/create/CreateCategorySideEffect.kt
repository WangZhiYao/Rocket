package com.yizhenwind.rocket.feature.categorysubject.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Category

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
sealed class CreateCategorySideEffect {

    data class ShowTitleError(@StringRes val resId: Int) : CreateCategorySideEffect()

    object HideTitleError : CreateCategorySideEffect()

    data class CreateCategorySuccess(val category: Category) : CreateCategorySideEffect()

    data class ShowSnack(@StringRes val resId: Int) : CreateCategorySideEffect()

}