package com.yizhenwind.rocket.core.mediator.character

import android.content.Context
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.core.model.CharacterProfile
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
interface ICharacterService {

    fun observeCharacterByClientId(clientId: Long): Flow<List<Character>>

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>>

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfile>>

    fun launchCreateCharacter(context: Context, clientId: Long, accountId: Long)

}