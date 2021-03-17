package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.data.repositories.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordBookViewModel(
    private val repository: WordRepository
): ViewModel() {
    fun insertOrUpdate(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertOrUpdate(word)
    }

    fun delete(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(word)
    }

    fun getAllWords() = repository.getAllWords()
}