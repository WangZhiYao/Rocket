package com.yizhenwind.rocket.data.character

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterDtoMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterProfileDtoMapper
import com.yizhenwind.rocket.core.database.mapper.SimpleCharacterMapper
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.core.model.simple.SimpleCharacter
import com.yizhenwind.rocket.data.character.source.CharacterLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterRepository @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource,
    private val characterDtoMapper: CharacterDtoMapper,
    private val characterProfileDtoMapper: CharacterProfileDtoMapper,
    private val characterMapper: CharacterMapper,
    private val simpleCharacterMapper: SimpleCharacterMapper
) {

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>> =
        characterLocalDataSource.observeCharacterProfileByClientId(clientId)
            .map { ListMapper(characterProfileDtoMapper).map(it) }

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfile>> =
        characterLocalDataSource.observeCharacterProfileByAccountId(accountId)
            .map { ListMapper(characterProfileDtoMapper).map(it) }

    fun createCharacter(character: Character): Flow<Character> =
        characterLocalDataSource.createCharacter(characterMapper.map(character))
            .map { id -> character.copy(id = id) }

    fun observeCharacterById(id: Long): Flow<Character> =
        characterLocalDataSource.observeCharacterById(id).map { characterDto ->
            characterDto?.run { characterDtoMapper.map(this) } ?: Character()
        }

    fun observeSimpleCharacterListByClientId(clientId: Long): Flow<List<SimpleCharacter>> =
        characterLocalDataSource.observeSimpleCharacterListByClientId(clientId)
            .map { ListMapper(simpleCharacterMapper).map(it) }

    fun observeSimpleCharacterListByAccountId(accountId: Long): Flow<List<SimpleCharacter>> =
        characterLocalDataSource.observeSimpleCharacterListByAccountId(accountId)
            .map { ListMapper(simpleCharacterMapper).map(it) }

}