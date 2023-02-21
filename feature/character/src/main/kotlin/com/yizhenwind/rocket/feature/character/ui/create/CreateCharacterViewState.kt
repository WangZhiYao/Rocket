package com.yizhenwind.rocket.feature.character.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.*
import com.yizhenwind.rocket.core.model.AccountTuple
import com.yizhenwind.rocket.core.model.ClientTuple

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class CreateCharacterViewState(
    val clientTupleList: List<ClientTuple> = emptyList(),
    val clientTuple: ClientTuple = ClientTuple(),
    val accountTupleList: List<AccountTuple> = emptyList(),
    val accountTuple: AccountTuple = AccountTuple(),
    val zoneServerList: List<ZoneServer> = emptyList(),
    val zoneList: List<Zone> = emptyList(),
    val zone: Zone = Zone(),
    val serverList: List<Server> = emptyList(),
    val server: Server = Server(),
    val sectInternalList: List<SectInternal> = emptyList(),
    val sectList: List<Sect> = emptyList(),
    val sect: Sect = Sect(),
    val internalList: List<Internal> = emptyList(),
    val internal: Internal = Internal(),
) : IViewState