package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.AccountEntity
import com.yizhenwind.rocket.core.model.Account
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class AccountMapper @Inject constructor() : IMapper<Account, AccountEntity> {

    override fun map(input: Account): AccountEntity =
        input.run {
            AccountEntity(
                id,
                client.id,
                username,
                password,
                encrypted,
                iv,
                enable,
                createTime
            )
        }
}