package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.AccountTupleDto
import com.yizhenwind.rocket.core.model.AccountTuple
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class AccountTupleMapper @Inject constructor() : IMapper<AccountTupleDto, AccountTuple> {

    override fun map(input: AccountTupleDto): AccountTuple =
        input.run { AccountTuple(id, username) }

}