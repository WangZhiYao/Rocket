package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.simple.SimpleAccountDto
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class SimpleAccountMapper @Inject constructor() : IMapper<SimpleAccountDto, SimpleAccount> {

    override fun map(input: SimpleAccountDto): SimpleAccount =
        input.run { SimpleAccount(id, username) }

}