package com.yizhenwind.rocket.domain.character.source

import androidx.paging.PagingConfig
import com.yizhenwind.rocket.core.database.dao.CharacterDao
import com.yizhenwind.rocket.core.database.di.qualifier.DatabasePagingConfig
import com.yizhenwind.rocket.core.database.dto.CharacterDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
class CharacterLocalSource @Inject constructor(
    private val characterDao: CharacterDao,
    @DatabasePagingConfig private val pagingConfig: PagingConfig
) {

    fun observeCharacterListByClientId(clientId: Long): Flow<List<CharacterDto>> =
        characterDao.observeCharacterListByClientId(clientId)

}