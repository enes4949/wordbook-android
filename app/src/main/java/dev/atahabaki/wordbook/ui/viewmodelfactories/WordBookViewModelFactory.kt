package dev.atahabaki.wordbook.ui.viewmodelfactories

import dagger.assisted.AssistedFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel

@AssistedFactory
interface WordBookViewModelFactory {
    fun create(): WordBookViewModel
}