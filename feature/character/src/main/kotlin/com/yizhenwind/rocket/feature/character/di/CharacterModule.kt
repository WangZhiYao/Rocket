package com.yizhenwind.rocket.feature.character.di

import com.yizhenwind.rocket.core.mediator.character.ICharacterService
import com.yizhenwind.rocket.feature.character.di.service.CharacterServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideCharacterService(characterServiceImpl: CharacterServiceImpl): ICharacterService =
        characterServiceImpl

}