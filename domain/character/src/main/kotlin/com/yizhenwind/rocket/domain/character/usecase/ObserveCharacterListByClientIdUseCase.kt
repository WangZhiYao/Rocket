package com.yizhenwind.rocket.domain.character.usecase

import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.domain.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class ObserveCharacterListByClientIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(clientId: Long): Flow<List<Character>> =
        characterRepository.observeCharacterListByClientId(clientId)

}