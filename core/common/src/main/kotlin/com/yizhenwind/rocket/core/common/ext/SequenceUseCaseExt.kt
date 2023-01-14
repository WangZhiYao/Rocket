package com.yizhenwind.rocket.core.common.ext

import com.yizhenwind.rocket.core.common.usecase.ExecuteResult
import com.yizhenwind.rocket.core.common.usecase.IUseCase
import com.yizhenwind.rocket.core.common.usecase.SequenceUseCase
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
operator fun SequenceUseCase.plusAssign(useCase: IUseCase<Flow<ExecuteResult>>) {
    add(useCase)
}