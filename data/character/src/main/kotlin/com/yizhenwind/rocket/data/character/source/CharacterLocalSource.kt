package com.yizhenwind.rocket.data.character.source

import com.yizhenwind.rocket.core.database.dao.CharacterDao
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import com.yizhenwind.rocket.core.database.dto.CharacterProfileDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class CharacterLocalSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    fun observeCharacterByClientId(clientId: Long): Flow<List<CharacterDto>> =
        characterDao.observeCharacterByClientId(clientId)

    fun observeCharacterProfileByClientId(clientId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(clientId)

    fun observeCharacterProfileByAccountId(accountId: Long): Flow<List<CharacterProfileDto>> =
        characterDao.observeCharacterProfileByClientId(accountId)
}