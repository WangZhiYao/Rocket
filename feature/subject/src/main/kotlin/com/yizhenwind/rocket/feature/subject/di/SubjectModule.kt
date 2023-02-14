package com.yizhenwind.rocket.feature.subject.di

import com.yizhenwind.rocket.core.mediator.subject.ISubjectService
import com.yizhenwind.rocket.feature.subject.di.service.SubjectServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
@Module
@InstallIn(SingletonComponent::class)
object SubjectModule {

    @Provides
    @Singleton
    fun provideSubjectService(
        subjectServiceImpl: SubjectServiceImpl
    ): ISubjectService = subjectServiceImpl

}