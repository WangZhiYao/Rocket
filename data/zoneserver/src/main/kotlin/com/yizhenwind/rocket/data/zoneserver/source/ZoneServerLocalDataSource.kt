package com.yizhenwind.rocket.data.zoneserver.source

import com.yizhenwind.rocket.core.database.dao.ZoneDao
import com.yizhenwind.rocket.core.database.dto.ZoneServerDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class ZoneServerLocalDataSource @Inject constructor(
    private val zoneDao: ZoneDao
) {

    fun observeZoneServer(): Flow<List<ZoneServerDto>> =
        zoneDao.observeZoneServer()

}