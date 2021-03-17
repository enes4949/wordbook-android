package dev.atahabaki.wordbook.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.atahabaki.wordbook.entities.WordItem

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_or_update(word: WordItem)

    @Delete
    suspend fun delete(word: WordItem)

    @Query("SELECT * FROM wordbook")
    fun getAllWords(): LiveData<List<WordItem>>
}