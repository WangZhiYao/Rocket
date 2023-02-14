package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.data.account.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
class ObserveAccountByIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(id: Long): Flow<Account> =
        accountRepository.observeAccountById(id).flowOn(dispatcher)

}