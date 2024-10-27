package com.devjethava.hiltboilerplate.di

import android.app.Application
import com.devjethava.hiltboilerplate.database.AppDatabase
import com.devjethava.hiltboilerplate.database.dao.UserDao
import com.devjethava.hiltboilerplate.database.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    fun provideUserDAO(db: AppDatabase): UserDao {
        return db.userDAO()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }
}