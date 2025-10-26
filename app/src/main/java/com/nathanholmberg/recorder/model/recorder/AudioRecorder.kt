package com.nathanholmberg.recorder.model.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}