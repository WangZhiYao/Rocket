package com.yizhenwind.rocket.core.mediator.account

import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
interface IAccountService {

    fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfile>>

    fun createAccount(account: Account): Flow<Account>

}