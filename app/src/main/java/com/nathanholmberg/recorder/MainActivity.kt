package com.nathanholmberg.recorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel

import com.nathanholmberg.recorder.ui.MainScreen
import com.nathanholmberg.recorder.viewmodel.MainViewModel
import com.nathanholmberg.recorder.model.player.AndroidAudioPlayer
import com.nathanholmberg.recorder.model.recorder.AndroidAudioRecorder

class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private val mainViewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(recorder, player, cacheDir) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Get Audio Permissions
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )

        // Set the composable content
        setContent {
            MainScreen(mainViewModel)
        }

        // Collect UI events emitted by ViewModel
        lifecycleScope.launch {
            mainViewModel.uiEvent.collect { event ->
                when (event) {
                    is MainViewModel.UIEvent.ShowAlert -> {
                        showAlert(event.title, event.message)
                    }
                }
            }
        }
    }
    // Function to display an AlertDialog
    private fun showAlert(title: String, message: String) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}
