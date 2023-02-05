package com.yizhenwind.rocket.data.sectinternal

import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.database.mapper.InternalMapper
import com.yizhenwind.rocket.core.database.mapper.SectMapper
import com.yizhenwind.rocket.core.model.SectInternal
import com.yizhenwind.rocket.data.sectinternal.source.SectInternalLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class SectInternalRepository @Inject constructor(
    private val sectInternalLocalDataSource: SectInternalLocalDataSource,
    private val sectMapper: SectMapper,
    private val internalMapper: InternalMapper
) {

    fun observeSectInternal(): Flow<List<SectInternal>> =
        sectInternalLocalDataSource.observeSectInternal()
            .map { sectInternalDtoList ->
                sectInternalDtoList.map { sectInternalDto ->
                    sectInternalDto.run {
                        SectInternal(
                            sectMapper.fromEntity(sectEntity),
                            EntityListMapper(internalMapper).fromEntity(internalEntityList)
                        )
                    }
                }
            }
}