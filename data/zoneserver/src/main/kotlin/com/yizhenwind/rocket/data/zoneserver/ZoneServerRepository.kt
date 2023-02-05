package com.yizhenwind.rocket.data.zoneserver

import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.database.mapper.ServerMapper
import com.yizhenwind.rocket.core.database.mapper.ZoneMapper
import com.yizhenwind.rocket.core.model.ZoneServer
import com.yizhenwind.rocket.data.zoneserver.source.ZoneServerLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class ZoneServerRepository @Inject constructor(
    private val zoneServerLocalDataSource: ZoneServerLocalDataSource,
    private val zoneMapper: ZoneMapper,
    private val serverMapper: ServerMapper
) {

    fun observeZoneServer(): Flow<List<ZoneServer>> =
        zoneServerLocalDataSource.observeZoneServer()
            .map { zoneServerDtoList ->
                zoneServerDtoList.map { zoneServerDto ->
                    zoneServerDto.run {
                        ZoneServer(
                            zoneMapper.fromEntity(zoneEntity),
                            EntityListMapper(serverMapper).fromEntity(serverEntityList)
                        )
                    }
                }
            }

}