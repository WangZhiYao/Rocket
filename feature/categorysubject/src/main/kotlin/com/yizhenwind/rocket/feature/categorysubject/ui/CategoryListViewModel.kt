package com.yizhenwind.rocket.feature.categorysubject.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.domain.category.DeleteCategoryUseCase
import com.yizhenwind.rocket.domain.category.ObserveCategoryListUseCase
import com.yizhenwind.rocket.feature.categorysubject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/26
 */
@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val observeCategoryListUseCase: ObserveCategoryListUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val logger: ILogger
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
                .catch {
                    logger.e(it)
                    postSideEffect(CategoryListSideEffect.ShowError(R.string.error_delete_category_failed))
                }
                .collect {
                    postSideEffect(CategoryListSideEffect.DeleteCategorySuccess)
                }
        }
    }
}