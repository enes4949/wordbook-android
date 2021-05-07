package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.data.repositories.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordBookViewModel @Inject internal constructor(
    private val repository: WordRepository
): ViewModel() {

    fun insertOrUpdate(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertOrUpdate(word)
    }

    fun delete(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(word)
    }

    fun deleteAll() = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteAll()
    }

    fun getAllWords() = repository.getAllWords()

    fun searchWords(content: String) = repository.searchWords(content)
}