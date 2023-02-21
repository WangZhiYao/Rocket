package com.yizhenwind.rocket.domain.common.usecase

import com.yizhenwind.rocket.core.common.ext.pickFirstOrDefault
import com.yizhenwind.rocket.core.common.usecase.IDataFlowUseCase
import com.yizhenwind.rocket.core.mediator.client.IClientService
import com.yizhenwind.rocket.core.model.ClientTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/19
 */
class ClientTupleUseCase @Inject constructor(
    private val clientService: IClientService
) : IDataFlowUseCase<TupleContext> {

    override fun execute(data: TupleContext): Flow<TupleContext> =
        clientService.observeClientTupleList()
            .map { clientTupleList ->
                data.copy(
                    clientTupleList = clientTupleList,
                    clientTuple = clientTupleList.pickFirstOrDefault(ClientTuple()) { it.id == data.clientTuple.id }
                )
            }
}