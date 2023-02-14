package com.yizhenwind.rocket.data.account

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.AccountDtoMapper
import com.yizhenwind.rocket.core.database.mapper.AccountMapper
import com.yizhenwind.rocket.core.database.mapper.AccountProfileDtoMapper
import com.yizhenwind.rocket.core.database.mapper.SimpleAccountMapper
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountProfile
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import com.yizhenwind.rocket.data.account.source.AccountLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class AccountRepository @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource,
    private val accountProfileDtoMapper: AccountProfileDtoMapper,
    private val accountMapper: AccountMapper,
    private val accountDtoMapper: AccountDtoMapper,
    private val simpleAccountMapper: SimpleAccountMapper
) {

    fun observeAccountListByClientId(clientId: Long): Flow<List<Account>> =
        accountLocalDataSource.observeAccountListByClientId(clientId)
            .map { ListMapper(accountDtoMapper).map(it) }

    fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfile>> =
        accountLocalDataSource.observeAccountProfileByClientId(clientId)
            .map { ListMapper(accountProfileDtoMapper).map(it) }

    fun createAccount(account: Account): Flow<Account> =
        accountLocalDataSource.createAccount(accountMapper.map(account))
            .map { id -> account.copy(id = id) }

    suspend fun getAccountByUsername(username: String): Account? =
        accountLocalDataSource.getAccountByUsername(username)
            ?.run { accountDtoMapper.map(this) }

    fun observeAccountById(id: Long): Flow<Account> =
        accountLocalDataSource.observeAccountById(id).map { accountEntity ->
            accountEntity?.run { accountDtoMapper.map(this) } ?: Account()
        }

    fun observeSimpleAccountList(): Flow<List<SimpleAccount>> =
        accountLocalDataSource.observeSimpleAccountList()
            .map { ListMapper(simpleAccountMapper).map(it) }

    fun observeSimpleAccountListByClientId(clientId: Long): Flow<List<SimpleAccount>> =
        accountLocalDataSource.observeSimpleAccountListByClientId(clientId)
            .map { ListMapper(simpleAccountMapper).map(it) }

}