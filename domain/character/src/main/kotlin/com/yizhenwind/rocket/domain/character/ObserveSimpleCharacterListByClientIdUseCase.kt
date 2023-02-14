package com.yizhenwind.rocket.domain.character

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
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
class ObserveSimpleCharacterListByClientIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(clientId: Long): Flow<List<SimpleCharacter>> =
        characterRepository.observeSimpleCharacterListByClientId(clientId).flowOn(dispatcher)

}