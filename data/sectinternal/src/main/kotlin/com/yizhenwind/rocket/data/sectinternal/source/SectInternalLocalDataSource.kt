package com.yizhenwind.rocket.data.sectinternal.source

import com.yizhenwind.rocket.core.database.dao.SectDao
import com.yizhenwind.rocket.core.database.dto.SectInternalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class SectInternalLocalDataSource @Inject constructor(
    private val sectDao: SectDao
) {

    fun observeSectInternal(): Flow<List<SectInternalDto>> =
        sectDao.observeSectInternal()

}