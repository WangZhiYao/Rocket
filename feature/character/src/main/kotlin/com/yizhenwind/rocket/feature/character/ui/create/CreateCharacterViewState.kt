package com.yizhenwind.rocket.feature.character.ui.create

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.*
import com.yizhenwind.rocket.core.model.simple.SimpleAccount
import com.yizhenwind.rocket.core.model.simple.SimpleClient

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class CreateCharacterViewState(
    val simpleClientList: List<SimpleClient> = emptyList(),
    val simpleClient: SimpleClient = SimpleClient(),
    val zoneServerList: List<ZoneServer> = emptyList(),
    val zoneList: List<Zone> = emptyList(),
    val zone: Zone = Zone(),
    val serverList: List<Server> = emptyList(),
    val server: Server = Server(),
    val simpleAccountList: List<SimpleAccount> = emptyList(),
    val simpleAccount: SimpleAccount = SimpleAccount(),
    val sectInternalList: List<SectInternal> = emptyList(),
    val sectList: List<Sect> = emptyList(),
    val sect: Sect = Sect(),
    val internalList: List<Internal> = emptyList(),
    val internal: Internal = Internal(),
) : IViewState