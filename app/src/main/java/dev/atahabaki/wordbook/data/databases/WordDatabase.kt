package dev.atahabaki.wordbook.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.atahabaki.wordbook.data.daos.WordDao
import dev.atahabaki.wordbook.data.entities.WordItem

@Database(
    entities = [WordItem::class],
    version = 1
)
abstract class WordDatabase: RoomDatabase() {
    abstract fun getWordDao(): WordDao
}