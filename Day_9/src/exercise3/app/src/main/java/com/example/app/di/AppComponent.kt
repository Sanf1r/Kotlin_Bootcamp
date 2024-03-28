package com.example.app.di

import com.example.app.fragments.CompaniesFragment
import com.example.app.fragments.CompanyDetailsFragment
import com.example.app.fragments.VacanciesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(fragment: CompaniesFragment)
    fun inject(fragment: CompanyDetailsFragment)
    fun inject(fragment: VacanciesFragment)

}