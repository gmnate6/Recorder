package com.nathanholmberg.recorder.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel: ViewModel() {
    private val _uiEvent = MutableSharedFlow<UIEvent>() // Internal event flow
    val uiEvent = _uiEvent.asSharedFlow()               // UI observes this flow for events
    private var audioFile: File? = null

    fun startRecording() {
    }

    fun stopRecording() {
    }

    fun startPlayback() {
    }

    fun stopPlayback() {
    }

    fun requestAlert(title: String, message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _uiEvent.emit(UIEvent.ShowAlert(title, message))
        }
    }

    sealed class UIEvent {
        data class ShowAlert(val title: String, val message: String) : UIEvent()
        // Add other events (e.g., navigation) here as needed
    }
}
