package dev.atahabaki.wordbook.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.atahabaki.wordbook.data.databases.WordDatabase
import dev.atahabaki.wordbook.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordBookAppModule {
    @Singleton
    @Provides
    fun provideWordBookDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, WordDatabase::class.java, Constants.WORDBOOK_DB_NAME).build()

    @Singleton
    @Provides
    fun provideWordBookDAO(db: WordDatabase) = db.getWordDao()
}