package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.AccountTuple
import com.yizhenwind.rocket.data.account.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class ObserveAccountTupleListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<AccountTuple>> =
        accountRepository.observeAccountTupleList().flowOn(dispatcher)

}