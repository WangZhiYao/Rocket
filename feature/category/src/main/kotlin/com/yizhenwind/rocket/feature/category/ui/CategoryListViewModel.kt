package com.yizhenwind.rocket.feature.category.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.domain.category.DeleteCategoryUseCase
import com.yizhenwind.rocket.domain.category.ObserveCategoryListUseCase
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
    private val deleteCategoryUseCase: DeleteCategoryUseCase
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

    fun deleteCategory(category: Category) {
        intent {
            deleteCategoryUseCase(category)
                .collect {
                    postSideEffect(CategoryListSideEffect.DeleteCategorySuccess)
                }
        }
    }
}