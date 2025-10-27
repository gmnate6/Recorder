package com.nathanholmberg.recorder.model

import com.google.cloud.speech.v1.*
import com.google.protobuf.ByteString
import java.io.File
import java.io.FileInputStream

// WARNING: Function will throw an error if something goes wrong
fun transcribeAudio(file: File): String? {
    // Initialize the SpeechClient
    val speechClient = SpeechClient.create()

    // Read the file into a ByteArray
    val inputStream = FileInputStream(file)
    val byteArray = inputStream.readBytes() // Reads all bytes from the file
    inputStream.close()

    // Convert the ByteArray to ByteString
    val byteString = ByteString.copyFrom(byteArray)

    // Configure the RecognitionAudio object
    val audio = RecognitionAudio.newBuilder().setContent(byteString).build()

    // Configure RecognitionConfig (modify encoding/settings as per the file format)
    val config = RecognitionConfig.newBuilder()
        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // Update to match your file format
        .setSampleRateHertz(16000) // Ensure this matches your input file
        .setLanguageCode("en-US")
        .build()

    // Create the RecognizeRequest
    val request = RecognizeRequest.newBuilder()
        .setConfig(config)
        .setAudio(audio)
        .build()

    // Send the request and handle the response
    val response = speechClient.recognize(request)

    // Iterate through the results and print transcript
    var transcript = ""
    response.resultsList.forEach { result ->
        result.alternativesList.forEach { alternative ->
            transcript += alternative.transcript
        }
    }

    // Close the SpeechClient to release resources
    speechClient.close()

    return transcript
}
