package com.yizhenwind.rocket.feature.account.di.service

import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.domain.account.CreateAccountUseCase
import com.yizhenwind.rocket.domain.account.ObserveAccountProfileByClientIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class AccountServiceImpl @Inject constructor(
    private val observeAccountProfileByClientIdUseCase: ObserveAccountProfileByClientIdUseCase,
    private val createAccountUseCase: CreateAccountUseCase
) : IAccountService {

    override fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfile>> =
        observeAccountProfileByClientIdUseCase(clientId)

    override fun createAccount(account: Account): Flow<Account> =
        createAccountUseCase(account)

}