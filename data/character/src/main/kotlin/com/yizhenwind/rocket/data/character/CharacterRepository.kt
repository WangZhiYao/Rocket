package com.yizhenwind.rocket.data.character

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterDtoMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterProfileDtoMapper
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.core.model.CharacterProfile
import com.yizhenwind.rocket.data.character.source.CharacterLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterRepository @Inject constructor(
    private val characterLocalSource: CharacterLocalSource,
    private val characterDtoMapper: CharacterDtoMapper,
    private val characterProfileDtoMapper: CharacterProfileDtoMapper
) {

    fun observeCharacterByClientId(clientId: Long): Flow<List<Character>> =
        characterLocalSource.observeCharacterByClientId(clientId)
            .map { ListMapper(characterDtoMapper).map(it) }

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfile>> =
        characterLocalSource.observeCharacterProfileByClientId(clientId)
            .map { ListMapper(characterProfileDtoMapper).map(it) }

}