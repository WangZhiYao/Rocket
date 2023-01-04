package com.yizhenwind.rocket.feature.character.di.service

import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.domain.character.usecase.ObserveCharacterListByClientIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class CharacterService @Inject constructor(
    private val observeCharacterByClientIdUseCase: ObserveCharacterListByClientIdUseCase
) : ICharacterService {

    override fun observeCharacterListByClientId(clientId: Long): Flow<List<Character>> =
        observeCharacterByClientIdUseCase(clientId)

}