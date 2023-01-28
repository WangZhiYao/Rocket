package com.yizhenwind.rocket.domain.account

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.data.account.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class ObserveAccountProfileByClientIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(clientId: Long): Flow<List<AccountProfile>> =
        accountRepository.observeAccountProfileByClientId(clientId)
            .flowOn(dispatcher)

}