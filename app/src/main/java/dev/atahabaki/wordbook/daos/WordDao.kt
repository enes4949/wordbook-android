package dev.atahabaki.wordbook.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.atahabaki.wordbook.entities.WordItem

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_or_update(word: WordItem)

    @Delete
    suspend fun delete(word: WordItem)
}