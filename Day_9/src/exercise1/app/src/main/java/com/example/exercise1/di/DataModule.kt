package com.example.exercise1.di

import com.example.data.RepositoryImpl
import com.example.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideRepositoryImpl(): Repository {
        return RepositoryImpl()
    }
}