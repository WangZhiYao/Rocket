package com.yizhenwind.rocket.feature.categorysubject.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Category

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
data class CategoryListViewState(val categoryList: List<Category> = emptyList()) : IViewState