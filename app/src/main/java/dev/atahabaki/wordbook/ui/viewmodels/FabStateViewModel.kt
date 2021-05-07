package dev.atahabaki.wordbook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FabStateViewModel @Inject internal constructor(): ViewModel() {

    private var mutableFabState = MutableLiveData<Boolean>()
    val fabState: LiveData<Boolean> get() = mutableFabState

    fun selectFabState(isHidden: Boolean) {
        mutableFabState.value = isHidden
    }
}