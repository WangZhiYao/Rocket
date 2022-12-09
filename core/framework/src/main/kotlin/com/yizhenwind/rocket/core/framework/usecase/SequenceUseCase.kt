package com.yizhenwind.rocket.core.framework.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import java.util.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/4
 */
class SequenceUseCase(
    val useCaseList: LinkedList<IUseCase> = LinkedList(),
    override val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : IUseCase {

    fun add(index: Int, useCase: IUseCase) =
        apply { this.apply { useCaseList.add(index, useCase) } }

    override fun execute(): Flow<ExecuteResult> {
        return useCaseList.removeFirst().execute()
            .flatMapLatest { executeResult ->
                if (executeResult == ExecuteResult.CONTINUE && useCaseList.isNotEmpty()) {
                    SequenceUseCase(useCaseList).execute()
                } else {
                    flowOf(executeResult)
                }
            }
    }
}

operator fun SequenceUseCase.plusAssign(useCase: IUseCase) {
    this.useCaseList.add(useCase)
}
