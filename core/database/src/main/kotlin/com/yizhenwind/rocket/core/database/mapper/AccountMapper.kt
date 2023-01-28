package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.AccountEntity
import com.yizhenwind.rocket.core.model.Account
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class AccountMapper @Inject constructor() : IEntityMapper<AccountEntity, Account> {

    override fun fromEntity(entity: AccountEntity): Account =
        entity.run { Account(id, clientId, username, password, enable, createTime) }

    override fun toEntity(model: Account): AccountEntity =
        model.run {
            AccountEntity(
                id,
                clientId,
                username,
                password,
                enable,
                createTime
            )
        }
}