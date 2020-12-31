package com.doggo.molly.doggr.di

import com.doggo.molly.doggr.data.repository.AuthRepository
import com.doggo.molly.doggr.data.repository.AuthRepositoryImpl
import com.doggo.molly.doggr.data.repository.DogRepository
import com.doggo.molly.doggr.data.repository.DogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideDogRepository(authRepository: AuthRepository): DogRepository {
        return DogRepositoryImpl(authRepository)
    }
}
