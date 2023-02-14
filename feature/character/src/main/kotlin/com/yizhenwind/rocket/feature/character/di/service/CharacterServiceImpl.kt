package com.yizhenwind.rocket.feature.character.di.service

import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
import com.yizhenwind.rocket.domain.character.ObserveCharacterProfileByAccountIdUseCase
import com.yizhenwind.rocket.domain.character.ObserveCharacterProfileByClientIdUseCase
import com.yizhenwind.rocket.domain.character.ObserveSimpleCharacterListByAccountIdUseCase
import com.yizhenwind.rocket.domain.character.ObserveSimpleCharacterListByClientIdUseCase
import com.yizhenwind.rocket.feature.character.ui.composite.CharacterCompositeActivity
import com.yizhenwind.rocket.feature.character.ui.composite.CharacterCompositeActivityArgs
import com.yizhenwind.rocket.feature.character.ui.create.CreateCharacterArgs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterServiceImpl @Inject constructor(
    private val observeCharacterProfileByClientIdUseCase: ObserveCharacterProfileByClientIdUseCase,
    private val observeCharacterProfileByAccountIdUseCase: ObserveCharacterProfileByAccountIdUseCase,
    private val observeSimpleCharacterListByClientIdUseCase: ObserveSimpleCharacterListByClientIdUseCase,
    private val observeSimpleCharacterListByAccountIdUseCase: ObserveSimpleCharacterListByAccountIdUseCase
) : ICharacterService {

    override fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>> =
        observeCharacterProfileByClientIdUseCase(clientId)

    override fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfile>> =
        observeCharacterProfileByAccountIdUseCase(accountId)

    override fun launchCreateCharacter(context: Context, clientId: Long, accountId: Long) {
        CreateCharacterArgs(clientId, accountId).launch(context)
    }

    override fun launchCharacterComposite(context: Context, characterId: Long) {
        context.startActivity(
            Intent(context, CharacterCompositeActivity::class.java).apply {
                replaceExtras(CharacterCompositeActivityArgs(characterId).toBundle())
            }
        )
    }

    override fun observeSimpleCharacterListByClientId(clientId: Long): Flow<List<SimpleCharacter>> =
        observeSimpleCharacterListByClientIdUseCase(clientId)

    override fun observeSimpleCharacterListByAccountId(accountId: Long): Flow<List<SimpleCharacter>> =
        observeSimpleCharacterListByAccountIdUseCase(accountId)

}