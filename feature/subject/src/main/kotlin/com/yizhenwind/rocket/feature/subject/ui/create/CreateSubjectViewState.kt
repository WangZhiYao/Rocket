package com.yizhenwind.rocket.feature.subject.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Category

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
data class CreateSubjectViewState(
    val categoryList: List<Category> = emptyList(),
    val category: Category = Category()
) : IViewState