# Movie Finder App

Movie Finder is an Android app that allows users to search for movies by title, view search results, and explore detailed information about movies.

## Features

- **Movie Search:** Users can search for movies by title.
- **Results Display:** Search results show basic information about the movies such as title, release year, media type, and movie poster.
- **Movie Details:** By clicking on a movie, users can view detailed information about the movie, including title, release year, director, runtime, genre, IMDb rating, and poster.
- **OMDb API:** This app uses the OMDb API, you can find it on https://www.omdbapi.com/.

## Requirements
- **Android Studio**: Make sure you have the latest version of Android Studio installed.
- **Kotlin**: The project is written in Kotlin.
- **Gradle**: This project uses Gradle for managing dependencies and builds.
- **Emulator**: The application can only run on the **Medium Phone API Vanilla Ice Cream** emulator or higher, that means Android 
                version 15.0+.

- **Internet** connection to access the API

## Installation

1. Clone this repository to your local machine:
   ```bash
    git clone <repository-url>
    ```

2. Open the project in Android Studio.

3. Sync the Gradle files.

## Running the Application
To run the application first start your emulator and after that run the app, or if you have
problems with running the emulator from Android Studio use the following command in the terminal:

```bash
C:\Users\Korisnik\AppData\Local\Android\Sdk\emulator\emulator.exe -avd Medium_Phone_API_VanillaIceCream -gpu swiftshader_indirect
```

and after that if the emulator is running properly, you can start the app.

##Project Structure

.
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── org/
│   │   │   │       └── unizd/
│   │   │   │           └── rma/
│   │   │   │               └── markanjevic/
│   │   │   │                   ├── activities/            # Application activities
│   │   │   │                   ├── adapters/              # RecyclerView adapters
│   │   │   │                   ├── handlerClass/          # API calls
│   │   │   │                   └── objects/               # Model objects (SearchMovie, Movie, etc.)
│   │   │   └── res/
│   │   │       ├── layout/        # XML layouts (UI)
│   │   │       └── values/        # Colors, strings, dimensions
│   └── build.gradle              # Project configuration
├── build.gradle                  # Main project configuration
└── settings.gradle                # Gradle settings


 
