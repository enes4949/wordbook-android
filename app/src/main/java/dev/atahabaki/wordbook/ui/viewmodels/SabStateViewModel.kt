package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SabStateViewModel: ViewModel() {
    private var mutableSabState = MutableLiveData<Boolean>()
    val sabState: LiveData<Boolean> get() = mutableSabState

    fun selectSabState(isHidden: Boolean) {
        mutableSabState.value = isHidden
    }
}