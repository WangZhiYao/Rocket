package com.yizhenwind.rocket.domain.common.usecase

import com.yizhenwind.rocket.core.model.AccountTuple
import com.yizhenwind.rocket.core.model.CharacterTuple
import com.yizhenwind.rocket.core.model.ClientTuple

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/19
 */
data class TupleContext(
    val clientTupleList: List<ClientTuple> = emptyList(),
    val clientTuple: ClientTuple = ClientTuple(),
    val accountTupleList: List<AccountTuple> = emptyList(),
    val accountTuple: AccountTuple = AccountTuple(),
    val characterTupleList: List<CharacterTuple> = emptyList(),
    val characterTuple: CharacterTuple = CharacterTuple()
)