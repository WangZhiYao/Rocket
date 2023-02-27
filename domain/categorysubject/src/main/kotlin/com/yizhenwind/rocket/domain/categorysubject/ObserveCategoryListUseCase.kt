package com.yizhenwind.rocket.domain.categorysubject

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Category
import com.yizhenwind.rocket.data.categorysubject.CategorySubjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
class ObserveCategoryListUseCase @Inject constructor(
    private val categorySubjectRepository: CategorySubjectRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<Category>> =
        categorySubjectRepository.observeCategoryList().flowOn(dispatcher)

}