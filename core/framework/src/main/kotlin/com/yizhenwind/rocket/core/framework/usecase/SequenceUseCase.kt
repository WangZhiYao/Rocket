package com.yizhenwind.rocket.core.framework.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import java.util.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/4
 */
class SequenceUseCase(
    private val useCaseList: LinkedList<IUseCase<Flow<ExecuteResult>>> = LinkedList(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : IUseCase<Flow<ExecuteResult>> {

    fun add(useCase: IUseCase<Flow<ExecuteResult>>) =
        apply { this.apply { useCaseList.add(useCase) } }

    fun add(index: Int, useCase: IUseCase<Flow<ExecuteResult>>) =
        apply { this.apply { useCaseList.add(index, useCase) } }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(): Flow<ExecuteResult> {
        return useCaseList.removeFirst().execute()
            .flatMapLatest { executeResult ->
                if (executeResult == ExecuteResult.CONTINUE && useCaseList.isNotEmpty()) {
                    SequenceUseCase(useCaseList).execute()
                } else {
                    flowOf(executeResult)
                }
            }
            .flowOn(dispatcher)
    }
}

operator fun SequenceUseCase.plusAssign(useCase: IUseCase<Flow<ExecuteResult>>) {
    add(useCase)
}
