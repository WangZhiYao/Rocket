package com.yizhenwind.rocket.domain.sect

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.SectInternal
import com.yizhenwind.rocket.data.sectinternal.SectInternalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
class ObserveSectInternalUseCase @Inject constructor(
    private val sectInternalRepository: SectInternalRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<SectInternal>> =
        sectInternalRepository.observeSectInternal().flowOn(dispatcher)

}