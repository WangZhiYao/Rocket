package com.yizhenwind.rocket.core.mediator.sectinternal

import com.yizhenwind.rocket.core.model.SectInternal
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
interface ISectInternalService {

    fun observeSectInternal(): Flow<List<SectInternal>>

}