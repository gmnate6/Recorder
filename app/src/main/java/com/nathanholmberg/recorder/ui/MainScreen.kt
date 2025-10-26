package com.nathanholmberg.recorder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.nathanholmberg.recorder.viewmodel.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    // Box to center everything vertically and horizontally
    Box(
        modifier = Modifier.fillMaxSize(), // Make the Box cover the full screen
        contentAlignment = Alignment.Center // Center all child elements
    ) {
        // Column to stack elements vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center elements within the column
        ) {
            Button(onClick = { mainViewModel.startRecording() }) {
                Text(text = "Start Recording")
            }
            Button(onClick = { mainViewModel.stopRecording() }) {
                Text(text = "Stop Recording")
            }
            Button(onClick = { mainViewModel.startPlayback() }) {
                Text(text = "Start Playback")
            }
            Button(onClick = { mainViewModel.stopPlayback() }) {
                Text(text = "Stop Playback")
            }
        }
    }
}
