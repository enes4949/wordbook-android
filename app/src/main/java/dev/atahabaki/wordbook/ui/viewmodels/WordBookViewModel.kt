package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedInject
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.data.repositories.WordRepository
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordBookViewModel @AssistedInject constructor(
    private val repository: WordRepository
): ViewModel() {

    companion object {
        fun provideFactory(
                assistedFactory: WordBookViewModelFactory
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create() as T
            }
        }
    }

    fun insertOrUpdate(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertOrUpdate(word)
    }

    fun delete(word: WordItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(word)
    }

    fun getAllWords() = repository.getAllWords()

    fun searchWords(content: String) = repository.searchWords(content)
}