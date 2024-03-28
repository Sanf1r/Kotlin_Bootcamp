package com.example.app.di

import com.example.domain.GetCompanyByIdUseCase
import com.example.domain.GetCompanyListUseCase
import com.example.domain.GetVacancyListUseCase
import com.example.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetCompanyListUseCase(repository: Repository): GetCompanyListUseCase {
        return GetCompanyListUseCase(repository)
    }

    @Provides
    fun provideGetCompanyByIdUseCase(repository: Repository): GetCompanyByIdUseCase {
        return GetCompanyByIdUseCase(repository)
    }

    @Provides
    fun provideGetVacancyListUseCase(repository: Repository): GetVacancyListUseCase {
        return GetVacancyListUseCase(repository)
    }
}