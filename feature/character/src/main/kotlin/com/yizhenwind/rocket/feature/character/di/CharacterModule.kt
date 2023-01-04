package com.yizhenwind.rocket.feature.character.di

import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.feature.character.di.service.CharacterService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterModule {

    @Binds
    abstract fun bindCharacterService(characterService: CharacterService): ICharacterService

}