package com.example.app.di

import com.example.app.CompaniesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(fragment: CompaniesFragment)
}