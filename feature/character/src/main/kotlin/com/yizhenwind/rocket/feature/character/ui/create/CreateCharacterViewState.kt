package com.yizhenwind.rocket.feature.character.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class CreateCharacterViewState(
    val clientList: List<Client> = emptyList(),
    val client: Client = Client(),
    val zoneServerList: List<ZoneServer> = emptyList(),
    val zoneList: List<Zone> = emptyList(),
    val zone: Zone = Zone(),
    val serverList: List<Server> = emptyList(),
    val server: Server = Server(),
    val accountList: List<Account> = emptyList(),
    val account: Account = Account(),
    val sectInternalList: List<SectInternal> = emptyList(),
    val sectList: List<Sect> = emptyList(),
    val sect: Sect = Sect(),
    val internalList: List<Internal> = emptyList(),
    val internal: Internal = Internal(),
) : IViewState