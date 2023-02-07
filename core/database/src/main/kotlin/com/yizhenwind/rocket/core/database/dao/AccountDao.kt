package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.AccountDto
import com.yizhenwind.rocket.core.database.dto.AccountProfileDto
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
    @Query("SELECT * FROM account WHERE client_id = :clientId AND enable = 1 ORDER BY create_time DESC")
    fun observeAccountListByClientId(clientId: Long): Flow<List<AccountDto>>

    @Transaction
    @Query("SELECT id, username, ( SELECT Count(*) FROM CHARACTER WHERE account_id = account.id ) AS character_count, ( SELECT Count(*) FROM `order` WHERE account_id = account.id ) AS order_count, create_time FROM account WHERE client_id = :clientId AND enable = 1 ORDER BY create_time DESC")
    fun observeAccountProfileByClientId(clientId: Long): Flow<List<AccountProfileDto>>

    @Transaction
    @Query("SELECT * FROM account WHERE username = :username LIMIT 1")
    suspend fun getAccountByUsername(username: String): AccountDto?

    @Transaction
    @Query("SELECT * FROM account WHERE id = :id LIMIT 1")
    fun observeAccountById(id: Long): Flow<AccountDto?>

}