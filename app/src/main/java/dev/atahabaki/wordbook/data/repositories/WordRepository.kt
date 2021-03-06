package dev.atahabaki.wordbook.data.repositories

import dev.atahabaki.wordbook.data.databases.WordDatabase
import dev.atahabaki.wordbook.data.entities.WordItem
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val db: WordDatabase
) {
    suspend fun insertOrUpdate(word: WordItem) = db.getWordDao().insert_or_update(word)

    suspend fun delete(word: WordItem) = db.getWordDao().delete(word)

    suspend fun deleteAll() = db.getWordDao().deleteAll()

    fun getAllWords() = db.getWordDao().getAllWords()

    fun searchWords(content: String) = db.getWordDao().searchWords(content)
}