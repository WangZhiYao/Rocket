package com.yizhenwind.rocket.feature.sectinternal.di.service

import com.yizhenwind.rocket.core.mediator.sectinternal.ISectInternalService
import com.yizhenwind.rocket.core.model.SectInternal
import com.yizhenwind.rocket.domain.sect.ObserveSectInternalUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class SectInternalServiceImpl @Inject constructor(
    private val observeSectInternalUseCase: ObserveSectInternalUseCase
) : ISectInternalService {

    override fun observeSectInternal(): Flow<List<SectInternal>> =
        observeSectInternalUseCase()

}