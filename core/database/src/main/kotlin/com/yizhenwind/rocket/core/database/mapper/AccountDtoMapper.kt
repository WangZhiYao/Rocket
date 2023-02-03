package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.AccountDto
import com.yizhenwind.rocket.core.model.Account
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/18
 */
class AccountDtoMapper @Inject constructor(
    private val clientDtoMapper: ClientDtoMapper,
) : IMapper<AccountDto, Account> {

    override fun map(input: AccountDto): Account =
        input.run {
            Account(
                accountEntity.id,
                clientDtoMapper.map(clientDto),
                accountEntity.username,
                accountEntity.password,
                accountEntity.encrypted,
                accountEntity.iv,
                accountEntity.enable,
                accountEntity.createTime
            )
        }

}