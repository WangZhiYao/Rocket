package com.yizhenwind.rocket.domain.common.usecase

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.common.ext.pickFirstOrDefault
import com.yizhenwind.rocket.core.common.usecase.IDataFlowUseCase
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.model.AccountTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/20
 */
class AccountTupleUseCase @Inject constructor(
    private val accountService: IAccountService
) : IDataFlowUseCase<TupleContext> {

    override fun execute(data: TupleContext): Flow<TupleContext> =
        data.clientTuple.run {
            if (id == Constant.DEFAULT_ID) {
                flowOf(
                    data.copy(
                        accountTupleList = emptyList(),
                        accountTuple = AccountTuple()
                    )
                )
            } else {
                accountService.observeAccountTupleListByClientId(id)
                    .map { accountTupleList ->
                        data.copy(
                            accountTupleList = accountTupleList,
                            accountTuple = accountTupleList.pickFirstOrDefault(AccountTuple()) { it.id == data.accountTuple.id }
                        )
                    }
            }
        }
}