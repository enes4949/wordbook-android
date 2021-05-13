package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UIViewModel: ViewModel() {
    //______________________________________________________
    // Floating Action Button State
    //------------------------------------------------------
    private var mutableFabState = MutableLiveData<Boolean>()
    val fabState: LiveData<Boolean> get() = mutableFabState

    fun selectFabState(isHidden: Boolean) {
        mutableFabState.value = isHidden
    }

    //______________________________________________________
    // Search Action Bar State
    //------------------------------------------------------
    private var mutableSabState = MutableLiveData<Boolean>()
    val sabState: LiveData<Boolean> get() = mutableSabState

    fun selectSabState(isHidden: Boolean) {
        mutableSabState.value = isHidden
    }
}