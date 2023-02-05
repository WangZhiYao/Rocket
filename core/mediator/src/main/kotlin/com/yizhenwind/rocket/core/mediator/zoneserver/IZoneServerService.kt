package com.yizhenwind.rocket.core.mediator.zoneserver

import com.yizhenwind.rocket.core.model.ZoneServer
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
interface IZoneServerService {

    fun observeZoneServer(): Flow<List<ZoneServer>>

}