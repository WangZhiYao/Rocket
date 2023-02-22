package com.yizhenwind.rocket.feature.category.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Category

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
data class CategoryListViewState(val categoryList: List<Category> = emptyList()) : IViewState