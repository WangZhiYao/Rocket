package com.yizhenwind.rocket.core.mediator.character

import android.content.Context
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.core.model.CharacterTuple
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
interface ICharacterService {

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>>

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfile>>

    fun launchCreateCharacter(context: Context, clientId: Long, accountId: Long)

    fun launchCharacterComposite(context: Context, characterId: Long)

    fun observeCharacterTupleListByClientId(clientId: Long): Flow<List<CharacterTuple>>

    fun observeCharacterTupleListByAccountId(accountId: Long): Flow<List<CharacterTuple>>

}