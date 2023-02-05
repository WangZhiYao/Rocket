package com.yizhenwind.rocket.data.character.source

import com.yizhenwind.rocket.core.database.dao.CharacterDao
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import com.yizhenwind.rocket.core.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    fun observeCharacterByClientId(clientId: Long): Flow<List<CharacterDto>> =
        characterDao.observeCharacterByClientId(clientId)

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(clientId)

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(accountId)

    fun createCharacter(characterEntity: CharacterEntity): Flow<Long> =
        flow {
            emit(characterDao.insert(characterEntity))
        }

}