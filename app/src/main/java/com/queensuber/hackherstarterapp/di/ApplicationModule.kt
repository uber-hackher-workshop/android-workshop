package com.queensuber.hackherstarterapp.di

import com.jakewharton.rxrelay3.PublishRelay
import com.queensuber.hackherstarterapp.data.Status
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideLoadingScreenRelay(): PublishRelay<Status> = PublishRelay.create()
}