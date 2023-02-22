package com.yizhenwind.rocket.feature.category.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.domain.category.ObserveCategoryListUseCase
import com.yizhenwind.rocket.domain.category.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/22
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val observeCategoryListUseCase: ObserveCategoryListUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : BaseMVIViewModel<CategoryListViewState, CategoryListSideEffect>() {

    override val container =
        container<CategoryListViewState, CategoryListSideEffect>(CategoryListViewState())

    init {
        intent {
            observeCategoryListUseCase()
                .collect { categoryList ->
                    reduce {
                        state.copy(categoryList = categoryList)
                    }
                }
        }
    }

    fun updateCategory(category: Category) {
        intent {
            updateCategoryUseCase(category)
                .collect { category ->
                    if (!category.enable) {
                        postSideEffect(CategoryListSideEffect.DeleteCategorySuccess(category))
                    }
                }
        }
    }
}