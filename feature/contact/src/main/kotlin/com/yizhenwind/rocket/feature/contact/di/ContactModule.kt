package com.yizhenwind.rocket.feature.contact.di

import com.yizhenwind.rocket.core.mediator.contact.IContactService
import com.yizhenwind.rocket.feature.contact.di.service.ContactService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ContactModule {

    @Binds
    abstract fun bindContactService(contactService: ContactService): IContactService

}