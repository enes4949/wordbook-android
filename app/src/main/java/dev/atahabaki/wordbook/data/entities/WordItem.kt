package dev.atahabaki.wordbook.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordbook")
data class WordItem(
    var content: String,
    var meaning: String
    ) {
    @PrimaryKey(autoGenerate = true)
    var wid: Int? = null
}