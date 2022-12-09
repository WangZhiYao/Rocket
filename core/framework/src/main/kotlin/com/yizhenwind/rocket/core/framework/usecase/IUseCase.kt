package com.yizhenwind.rocket.core.framework.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/4
 */
interface IUseCase {

    val dispatcher: CoroutineDispatcher

    fun execute(): Flow<ExecuteResult>

}