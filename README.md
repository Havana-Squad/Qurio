# Qurio 

A modern Android trivia quiz application built with MVP architecture to challenge your knowledge, earn points, and unlock rewards!

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Setup](#setup)

## Overview
Qurio is an engaging trivia quiz app for Android that lets users test their knowledge across various categories like Music, Geography, History, and more. With gamification elements such as daily streaks, character unlocks, and a time-based scoring system, it keeps users coming back for daily challenges. Built with a focus on smooth animations and a modern UI, Qurio provides an immersive experience for trivia enthusiasts.

This app is developed as part of the Havana Squad project and uses the Open Trivia Database API for dynamic questions.

## Features
- ✨ Multiple Trivia Categories: Dive into topics like Music, Food & Drink, Geography, History, and beyond.
- 🧑‍🎨 Character Selection System: Choose and unlock unique avatars to personalize your profile.
- 🏆 Points and Rewards System: Earn points for correct answers and climb the leaderboards.
- 🔥 Daily Streak Tracking: Build and maintain streaks for bonus rewards.
- 📊 Game History and Performance Tracking: Review past quizzes and track your progress over time.
- ⏱️ Time-Based Scoring System: Race against the clock for higher scores.
- 🎨 Smooth Animations and Modern UI: Enjoy fluid transitions and a clean, intuitive design.

## Screenshots

| | | |
|:--:|:--:|:--:|
| <img src="https://github.com/user-attachments/assets/8d99a4f0-f1a4-45f0-aef4-2605bd549d04" width="200"/> | <img src="https://github.com/user-attachments/assets/a1b5607c-df53-4445-999c-1a9fc572f1b5" width="200"/> | <img src="https://github.com/user-attachments/assets/c49e2a5e-8015-43b5-85bd-37b7b10cb260" width="200"/> |
| <img src="https://github.com/user-attachments/assets/0ed5109a-b36f-40f7-9248-ba2cf90e5426" width="200"/> | <img src="https://github.com/user-attachments/assets/0c9ea565-98c3-4b12-880c-62ca67bc1f26" width="200"/> | <img src="https://github.com/user-attachments/assets/03750fde-2e8a-459b-a176-bd167b2249f4" width="200"/> |
| <img src="https://github.com/user-attachments/assets/b7c04293-78ee-46bc-913e-0f98070236e3" width="200"/> | <img src="https://github.com/user-attachments/assets/153dd06d-eb5a-4748-97a5-bfaed2e4fb09" width="200"/> | <img src="https://github.com/user-attachments/assets/38b3fe86-63a9-445a-9b77-e03a6312b8cc" width="200"/> |

[Demo](https://drive.google.com/file/d/1YIb1v9tOz5LbJB7hbO4G_yUnz9R89WZS/view?usp=sharing)  


## Tech Stack
| Category | Technology |
|----------|------------|
| Language | Kotlin |
| Architecture | MVP (Model-View-Presenter) |
| UI Components | XML Layouts, Fragments, View Binding |
| Navigation | Jetpack Navigation Component |
| Networking | Retrofit |
| Database | Room |
| Dependency Injection | Dagger 2 |
| Async Handling | Kotlin Coroutines |

## Architecture
<img width="1270" height="1010" alt="MVP drawio (1)" src="https://github.com/user-attachments/assets/2bc38aab-e021-490d-952f-de08ab7677f7" />


| Layer | Responsibility | Key Components |
|-------|----------------|----------------|
| Model | Handles data operations, including API calls and local storage. | Repositories, Data Models, Room DAOs |
| View | Manages UI rendering and user interactions (Activities/Fragments). | XML Views, Adapters, View Binding |
| Presenter | Acts as intermediary: processes inputs from View, fetches data from Model, and updates View. | Presenters for Quiz, Profile, History screens |

## Setup
To get started with Qurio locally:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Havana-Squad/Qurio.git
   cd Qurio
   ```

2. **Sync Gradle:**
   - Open the project in Android Studio and let Gradle sync.

**Requirements:** Android Studio (latest), SDK 21+, Gradle 7.0+.

## Contributors
<a href="https://github.com/Havana-Squad/Qurio/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Havana-Squad/Qurio" />
</a>
