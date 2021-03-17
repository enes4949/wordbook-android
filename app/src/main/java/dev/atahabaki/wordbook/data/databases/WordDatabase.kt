package dev.atahabaki.wordbook.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.atahabaki.wordbook.data.daos.WordDao
import dev.atahabaki.wordbook.data.entities.WordItem

@Database(
    entities = [WordItem::class],
    version = 1
)
abstract class WordDatabase: RoomDatabase() {

    abstract fun getWordDao(): WordDao

    companion object {
        @Volatile
        private var instance: WordDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it as WordDatabase
            }
        }

        private fun createDatabase(context: Context) {
            Room.databaseBuilder(context, WordDatabase::class.java,"wordbook.db").build()
        }
    }
}