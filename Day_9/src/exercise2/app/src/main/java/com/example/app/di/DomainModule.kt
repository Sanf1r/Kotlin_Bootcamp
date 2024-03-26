package com.example.app.di

import com.example.domain.GetCompanyListUseCase
import com.example.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetCompanyListUseCase(repository: Repository): GetCompanyListUseCase {
        return GetCompanyListUseCase(repository)
    }
}