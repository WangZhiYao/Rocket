package com.yizhenwind.rocket.core.mediator.category

import com.yizhenwind.rocket.core.model.Category
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
interface ICategoryService {

    fun observeCategoryList(): Flow<List<Category>>

}