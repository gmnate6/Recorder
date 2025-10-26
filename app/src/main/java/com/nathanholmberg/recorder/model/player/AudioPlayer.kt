package com.nathanholmberg.recorder.model.player

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}