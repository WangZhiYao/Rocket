package com.yizhenwind.rocket.core.mediator.character

import com.yizhenwind.rocket.core.model.Character
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
interface ICharacterService {

    fun observeCharacterListByClientId(clientId: Long): Flow<List<Character>>

}