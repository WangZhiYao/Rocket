package com.yizhenwind.rocket.core.mediator.account

import android.content.Context
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
interface IAccountService {

    fun observeAccountListByClientId(clientId: Long): Flow<List<Account>>

    fun observeSimpleAccountListByClientId(clientId: Long): Flow<List<SimpleAccount>>

    fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfile>>

    fun createAccount(account: Account): Flow<Account>

    fun launchCreateAccount(context: Context, clientId: Long)

    fun launchAccountComposite(context: Context, clientId: Long, accountId: Long)

}