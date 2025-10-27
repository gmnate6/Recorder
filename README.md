# Recorder - Voice Recording and Transcription App

<div align="center">
  <img src="assets/launcher.png" alt="App Icon" width="150">
</div>

## Overview
The Recorder app is designed to robustly handle voice recordings and convert them into transcripts. It aims to provide an intuitive way to record audio in the foreground and process the recorded chunks into meaningful transcriptions, suitable for summaries and other tasks.

## Features Implemented
- **Audio Recording**: Robust audio recording with support for background operation and playback functionality.
- **Audio Transcription**: Utilized `com.google.cloud.speech.v1` for converting audio into text transcripts. Encountered an issue with authenticating the transcription service.

## Tech Stack
- **Kotlin**: For modern, concise, and robust application development.
- **Jetpack Compose**: To build a fully declarative and reactive UI.
- **MVVM Architecture**: ViewModel → Repository → DAO/API setup provides separation of concerns and clean architecture. Significant effort was expended on refining and implementing this structure.
- **Coroutines & Flow**: Handles asynchronous operations and state management efficiently.

## Challenges
- While the transcription process was implemented using `com.google.cloud.speech.v1`, authentication issues prevented further testing and integration.
- Significant effort was invested in laying out the MVVM architecture to ensure proper flow and separation of concerns.

## Next Steps
- Resolve authentication issues with the transcription API to enable full functionality.
- Build and integrate transcript summary generation using an LLM API.

This application provides a robust foundation for voice recording and transcription functionalities and highlights a modern Android development approach.

## Demo Video

[Watch the demo video](assets/video.mp4)

