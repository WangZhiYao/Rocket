package com.yizhenwind.rocket.feature.contacttype.di

import com.yizhenwind.rocket.core.mediator.contacttype.IContactTypeService
import com.yizhenwind.rocket.feature.contacttype.di.service.ContactTypeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Module
@InstallIn(SingletonComponent::class)
object ContactTypeModule {

    @Provides
    @Singleton
    fun provideContactTypeList(contactTypeServiceImpl: ContactTypeServiceImpl): IContactTypeService =
        contactTypeServiceImpl

}