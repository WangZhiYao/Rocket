package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.AccountDto
import com.yizhenwind.rocket.core.database.dto.AccountTupleDto
import com.yizhenwind.rocket.core.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/15
 */
@Dao
interface AccountDao : IDao<AccountEntity> {

    @Transaction
    @Query("SELECT * FROM account WHERE client_id = :clientId ORDER BY create_time DESC")
    fun observeAccountListByClientId(clientId: Long): Flow<List<AccountDto>>

    @Transaction
    @Query("SELECT * FROM account WHERE username = :username LIMIT 1")
    suspend fun getAccountByUsername(username: String): AccountDto?

    @Transaction
    @Query("SELECT * FROM account WHERE id = :id LIMIT 1")
    fun observeAccountById(id: Long): Flow<AccountDto?>

    @Query("SELECT id, username FROM account")
    fun observeAccountTupleList(): Flow<List<AccountTupleDto>>

    @Query("SELECT id, username FROM account WHERE client_id = :clientId")
    fun observeAccountTupleListByClientId(clientId: Long): Flow<List<AccountTupleDto>>

}