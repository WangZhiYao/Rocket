package com.yizhenwind.rocket.core.common.usecase

import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/20
 */
interface IDataFlowUseCase<T> {

    fun execute(data: T): Flow<T>

}