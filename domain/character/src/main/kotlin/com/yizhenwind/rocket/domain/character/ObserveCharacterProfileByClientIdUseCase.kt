package com.yizhenwind.rocket.domain.character

import com.yizhenwind.rocket.core.common.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.data.character.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
class ObserveCharacterProfileByClientIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(clientId: Long): Flow<List<CharacterProfile>> =
        characterRepository.observeCharacterProfileByClientId(clientId)
            .flowOn(dispatcher)

}