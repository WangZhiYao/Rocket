package com.yizhenwind.rocket.feature.categorysubject.ui.subject

import androidx.annotation.StringRes

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
sealed class SubjectListSideEffect {

    object DeleteSubjectSuccess : SubjectListSideEffect()

    data class ShowError(@StringRes val resId: Int) : SubjectListSideEffect()

}