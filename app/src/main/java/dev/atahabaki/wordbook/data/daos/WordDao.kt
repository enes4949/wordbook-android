package dev.atahabaki.wordbook.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.atahabaki.wordbook.data.entities.WordItem

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_or_update(word: WordItem)

    @Delete
    suspend fun delete(word: WordItem)

    @Query("DELETE FROM wordbook")
    suspend fun deleteAll()

    @Query("SELECT * FROM wordbook")
    fun getAllWords(): LiveData<List<WordItem>>

    @Query("SELECT * FROM wordbook WHERE LOWER(content) LIKE '%' || :content || '%' OR LOWER(meaning) LIKE '%' || :content || '%'")
    fun searchWords(content: String): LiveData<List<WordItem>>?
}