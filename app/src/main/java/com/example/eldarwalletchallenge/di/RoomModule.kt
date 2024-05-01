package com.example.eldarwalletchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.eldarwalletchallenge.data.database.EldarWalletDatabase
import com.example.eldarwalletchallenge.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val ELDAR_WALLET_DATABASE = "ELDAR_WALLET_DATABASE"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): EldarWalletDatabase =
        Room.databaseBuilder(
            context,
            EldarWalletDatabase::class.java,
            ELDAR_WALLET_DATABASE
        ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: EldarWalletDatabase): UserDao = db.getUserDao()

}