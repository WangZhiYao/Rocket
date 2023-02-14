package com.yizhenwind.rocket.feature.subject.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Subject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
sealed class CreateSubjectSideEffect {

    data class ShowCategoryError(@StringRes val resId: Int) : CreateSubjectSideEffect()

    object HideCategoryError : CreateSubjectSideEffect()

    data class ShowContentError(@StringRes val resId: Int) : CreateSubjectSideEffect()

    object HideContentError : CreateSubjectSideEffect()

    data class CreateSubjectSuccess(val subject: Subject) : CreateSubjectSideEffect()

    data class CreateSubjectFailed(@StringRes val resId: Int) : CreateSubjectSideEffect()

}