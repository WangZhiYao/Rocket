package com.yizhenwind.rocket.data.account

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.AccountDtoMapper
import com.yizhenwind.rocket.core.database.mapper.AccountMapper
import com.yizhenwind.rocket.core.database.mapper.AccountTupleMapper
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountTuple
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
    private val accountMapper: AccountMapper,
    private val accountDtoMapper: AccountDtoMapper,
    private val accountTupleMapper: AccountTupleMapper
) {

    fun observeAccountListByClientId(clientId: Long): Flow<List<Account>> =
        accountLocalDataSource.observeAccountListByClientId(clientId)
            .map { ListMapper(accountDtoMapper).map(it) }

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

    fun observeAccountTupleList(): Flow<List<AccountTuple>> =
        accountLocalDataSource.observeAccountTupleList()
            .map { ListMapper(accountTupleMapper).map(it) }

    fun observeAccountTupleListByClientId(clientId: Long): Flow<List<AccountTuple>> =
        accountLocalDataSource.observeAccountTupleListByClientId(clientId)
            .map { ListMapper(accountTupleMapper).map(it) }

}