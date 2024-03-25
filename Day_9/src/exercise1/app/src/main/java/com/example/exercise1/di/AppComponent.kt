package com.example.exercise1.di

import com.example.data.RepositoryImpl
import com.example.exercise1.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}