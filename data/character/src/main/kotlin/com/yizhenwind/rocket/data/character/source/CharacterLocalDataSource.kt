package com.yizhenwind.rocket.data.character.source

import com.yizhenwind.rocket.core.database.dao.CharacterDao
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import com.yizhenwind.rocket.core.database.dto.CharacterTupleDto
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

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(clientId)

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(accountId)

    fun createCharacter(characterEntity: CharacterEntity): Flow<Long> =
        flow {
            emit(characterDao.insert(characterEntity))
        }

    fun observeCharacterById(id: Long): Flow<CharacterDto?> =
        characterDao.observeCharacterById(id)

    fun observeCharacterTupleListByClientId(clientId: Long): Flow<List<CharacterTupleDto>> =
        characterDao.observeCharacterTupleListByClientId(clientId)

    fun observeCharacterTupleListByAccountId(accountId: Long): Flow<List<CharacterTupleDto>> =
        characterDao.observeCharacterTupleListByAccountId(accountId)

}