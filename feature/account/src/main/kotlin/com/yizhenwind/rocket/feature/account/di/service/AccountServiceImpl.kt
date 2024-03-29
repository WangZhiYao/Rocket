package com.yizhenwind.rocket.feature.account.di.service

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.mediator.account.IAccountService
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountTuple
import com.yizhenwind.rocket.domain.account.CreateAccountUseCase
import com.yizhenwind.rocket.domain.account.ObserveAccountListByClientIdUseCase
import com.yizhenwind.rocket.domain.account.ObserveAccountTupleListByClientIdUseCase
import com.yizhenwind.rocket.feature.account.ui.composite.AccountCompositeActivity
import com.yizhenwind.rocket.feature.account.ui.composite.AccountCompositeActivityArgs
import com.yizhenwind.rocket.feature.account.ui.create.CreateAccountArgs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class AccountServiceImpl @Inject constructor(
    private val observeAccountListByClientIdUseCase: ObserveAccountListByClientIdUseCase,
    private val observeAccountTupleListByClientIdUseCase: ObserveAccountTupleListByClientIdUseCase,
    private val createAccountUseCase: CreateAccountUseCase
) : IAccountService {

    override fun observeAccountListByClientId(clientId: Long): Flow<List<Account>> =
        observeAccountListByClientIdUseCase(clientId)

    override fun observeAccountTupleListByClientId(clientId: Long): Flow<List<AccountTuple>> =
        observeAccountTupleListByClientIdUseCase(clientId)

    override fun createAccount(account: Account): Flow<Account> =
        createAccountUseCase(account)

    override fun launchCreateAccount(context: Context, clientId: Long) {
        CreateAccountArgs(clientId).launch(context)
    }

    override fun launchAccountComposite(context: Context, clientId: Long, accountId: Long) {
        context.startActivity(Intent(context, AccountCompositeActivity::class.java).apply {
            replaceExtras(AccountCompositeActivityArgs(accountId).toBundle())
        })
    }

}