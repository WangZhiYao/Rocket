package com.yizhenwind.rocket.core.common.usecase

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import java.util.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/20
 */
class DataFlowSequenceUseCase<T>(
    private val dataFlowUseCaseList: LinkedList<IDataFlowUseCase<T>> = LinkedList()
) : IDataFlowUseCase<T> {

    fun add(useCase: IDataFlowUseCase<T>) =
        apply { this.apply { dataFlowUseCaseList.add(useCase) } }

    fun add(index: Int, useCase: IDataFlowUseCase<T>) =
        apply { this.apply { dataFlowUseCaseList.add(index, useCase) } }

    @OptIn(FlowPreview::class)
    override fun execute(data: T): Flow<T> =
        dataFlowUseCaseList.removeFirst().execute(data)
            .flatMapConcat { result ->
                if (dataFlowUseCaseList.isNotEmpty()) {
                    DataFlowSequenceUseCase(dataFlowUseCaseList).execute(result)
                } else {
                    flowOf(result)
                }
            }

}