package com.yizhenwind.rocket.data.account

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.AccountMapper
import com.yizhenwind.rocket.core.database.mapper.AccountProfileDtoMapper
import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.AccountProfile
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
    private val accountMapper: AccountMapper
) {

    fun observeAccountByClientId(clientId: Long): Flow<List<Account>> =
        accountLocalDataSource.observeAccountByClientId(clientId)
            .map { EntityListMapper(accountMapper).fromEntity(it) }

    fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfile>> =
        accountLocalDataSource.observeAccountProfileByClientId(clientId)
            .map { ListMapper(accountProfileDtoMapper).map(it) }

    fun createAccount(account: Account): Flow<Account> =
        accountLocalDataSource.createAccount(accountMapper.toEntity(account))
            .map { id -> account.copy(id = id) }

    suspend fun getAccountByUsername(username: String): Account? =
        accountLocalDataSource.getAccountByUsername(username)
            ?.run { accountMapper.fromEntity(this) }

}