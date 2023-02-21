package com.yizhenwind.rocket.domain.character

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.CharacterTuple
import com.yizhenwind.rocket.data.character.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class ObserveCharacterTupleListByClientIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(clientId: Long): Flow<List<CharacterTuple>> =
        characterRepository.observeCharacterTupleListByClientId(clientId).flowOn(dispatcher)

}