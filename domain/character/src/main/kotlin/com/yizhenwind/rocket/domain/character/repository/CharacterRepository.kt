package com.yizhenwind.rocket.domain.character.repository

import com.yizhenwind.rocket.core.common.mapper.ListMapper
import com.yizhenwind.rocket.core.database.mapper.CharacterDtoMapper
import com.yizhenwind.rocket.core.model.Character
import com.yizhenwind.rocket.domain.character.source.CharacterLocalSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class CharacterRepository @Inject constructor(
    private val characterLocalSource: CharacterLocalSource,
    private val characterDtoMapper: CharacterDtoMapper,
) {

    fun observeCharacterListByClientId(clientId: Long): Flow<List<Character>> =
        characterLocalSource.observeCharacterListByClientId(clientId)
            .map {
                ListMapper(characterDtoMapper).map(it)
            }
            .flowOn(Dispatchers.IO)
}