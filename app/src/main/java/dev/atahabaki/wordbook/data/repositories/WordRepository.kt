package dev.atahabaki.wordbook.data.repositories

import dev.atahabaki.wordbook.data.databases.WordDatabase
import dev.atahabaki.wordbook.data.entities.WordItem

class WordRepository(
    private val db: WordDatabase
) {
    suspend fun insertOrUpdate(word: WordItem) = db.getWordDao().insert_or_update(word)

    suspend fun delete(word: WordItem) = db.getWordDao().delete(word)

    fun getAllWords() = db.getWordDao().getAllWords()
}