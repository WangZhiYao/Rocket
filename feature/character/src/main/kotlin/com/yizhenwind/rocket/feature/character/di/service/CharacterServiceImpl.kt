package com.yizhenwind.rocket.feature.character.di.service

import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.domain.character.ObserveCharacterByClientIdUseCase
import com.yizhenwind.rocket.domain.character.ObserveCharacterProfileByAccountIdUseCase
import com.yizhenwind.rocket.domain.character.ObserveCharacterProfileByClientIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterServiceImpl @Inject constructor(
    private val observeCharacterByClientIdUseCase: ObserveCharacterByClientIdUseCase,
    private val observeCharacterProfileByClientIdUseCase: ObserveCharacterProfileByClientIdUseCase,
    private val observeCharacterProfileByAccountIdUseCase: ObserveCharacterProfileByAccountIdUseCase
) : ICharacterService {

    override fun observeCharacterByClientId(clientId: Long): Flow<List<Character>> =
        observeCharacterByClientIdUseCase(clientId)

    override fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>> =
        observeCharacterProfileByClientIdUseCase(clientId)

    override fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfile>> =
        observeCharacterProfileByAccountIdUseCase(accountId)

}