package com.yizhenwind.rocket.data.account.source

import com.yizhenwind.rocket.core.database.dao.AccountDao
import com.yizhenwind.rocket.core.database.dto.AccountDto
import com.yizhenwind.rocket.core.database.dto.AccountTupleDto
import com.yizhenwind.rocket.core.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class AccountLocalDataSource @Inject constructor(
    private val accountDao: AccountDao
) {

    fun observeAccountListByClientId(clientId: Long): Flow<List<AccountDto>> =
        accountDao.observeAccountListByClientId(clientId)

    fun createAccount(accountEntity: AccountEntity): Flow<Long> =
        flow {
            emit(accountDao.insert(accountEntity))
        }

    suspend fun getAccountByUsername(username: String): AccountDto? =
        accountDao.getAccountByUsername(username)

    fun observeAccountById(id: Long): Flow<AccountDto?> =
        accountDao.observeAccountById(id)

    fun observeAccountTupleList(): Flow<List<AccountTupleDto>> =
        accountDao.observeAccountTupleList()

    fun observeAccountTupleListByClientId(clientId: Long): Flow<List<AccountTupleDto>> =
        accountDao.observeAccountTupleListByClientId(clientId)

    fun deleteAccount(accountEntity: AccountEntity): Flow<Int> =
        flow {
            emit(accountDao.delete(accountEntity))
        }

}