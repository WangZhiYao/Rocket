package com.yizhenwind.rocket.core.framework.ext

import com.yizhenwind.rocket.core.framework.usecase.ExecuteResult
import com.yizhenwind.rocket.core.framework.usecase.IUseCase
import com.yizhenwind.rocket.core.framework.usecase.SequenceUseCase
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
operator fun SequenceUseCase.plusAssign(useCase: IUseCase<Flow<ExecuteResult>>) {
    add(useCase)
}