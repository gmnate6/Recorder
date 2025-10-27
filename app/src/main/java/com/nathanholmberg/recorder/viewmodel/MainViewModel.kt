package com.nathanholmberg.recorder.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import kotlinx.coroutines.flow.asStateFlow

import com.nathanholmberg.recorder.model.player.AudioPlayer
import com.nathanholmberg.recorder.model.recorder.AudioRecorder
import com.nathanholmberg.recorder.model.transcribeAudio

class MainViewModel(
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer,
    private val audioDir: File
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UIEvent>() // Internal event flow
    val uiEvent = _uiEvent.asSharedFlow()               // UI observes this flow for events

    private var audioFile: File? = null

    private val _transcription = MutableStateFlow("")
    val transcription = _transcription.asStateFlow()

    fun updateTranscription(newTranscription: String) {
        _transcription.value = newTranscription
    }

    fun startRecording() {
        File(audioDir, "audio.mp3").also {
            audioRecorder.start(it)
            audioFile = it
        }
        updateTranscription("Recording...")
    }

    fun stopRecording() {
        if (audioFile == null) {
            return
        }

        audioRecorder.stop()

        var transcript = transcribeAudio(audioFile!!)
        if (transcript.isNullOrEmpty()) {
            transcript = "Unable to transcribe audio."
        }

        updateTranscription(transcript )
    }

    fun startPlayback() {
        audioPlayer.playFile(audioFile ?: return)
    }

    fun stopPlayback() {
        audioPlayer.stop()
    }

    fun requestAlert(title: String, message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _uiEvent.emit(UIEvent.ShowAlert(title, message))
        }
    }

    sealed class UIEvent {
        data class ShowAlert(val title: String, val message: String) : UIEvent()
        // Add other events here as needed
    }
}
