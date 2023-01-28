package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.AccountProfileDto
import com.yizhenwind.rocket.core.model.AccountProfile
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class AccountProfileDtoMapper @Inject constructor() : IMapper<AccountProfileDto, AccountProfile> {

    override fun map(input: AccountProfileDto): AccountProfile =
        input.run { AccountProfile(id, username, characterCount, orderCount, createTime) }

}