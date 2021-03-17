package dev.atahabaki.wordbook.ui.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.atahabaki.wordbook.data.repositories.WordRepository
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel

class WordBookViewModelFactory(
    private val repository: WordRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordBookViewModel(repository) as T
    }
}