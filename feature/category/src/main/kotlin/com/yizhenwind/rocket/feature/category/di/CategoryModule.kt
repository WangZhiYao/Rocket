package com.yizhenwind.rocket.feature.category.di

import com.yizhenwind.rocket.core.mediator.category.ICategoryService
import com.yizhenwind.rocket.feature.category.di.service.CategoryServiceImpl
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
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryService(categoryServiceImpl: CategoryServiceImpl): ICategoryService =
        categoryServiceImpl

}