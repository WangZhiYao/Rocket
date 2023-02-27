package com.yizhenwind.rocket.feature.categorysubject.di

import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.core.mediator.subject.ISubjectService
import com.yizhenwind.rocket.feature.categorysubject.di.service.CategoryServiceImpl
import com.yizhenwind.rocket.feature.categorysubject.di.service.SubjectServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
@Module
@InstallIn(SingletonComponent::class)
object CategorySubjectModule {

    @Provides
    @Singleton
    fun provideCategoryService(categoryServiceImpl: CategoryServiceImpl): ICategoryService =
        categoryServiceImpl

    @Provides
    @Singleton
    fun provideSubjectService(subjectServiceImpl: SubjectServiceImpl): ISubjectService =
        subjectServiceImpl

}