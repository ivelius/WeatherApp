[![CircleCI](https://circleci.com/gh/ivelius/WeatherApp.svg?style=svg)](https://circleci.com/gh/ivelius/WeatherApp)
# WeatherApp
This is a very simple app that fetches minimal weather information based on the location of the user.
![](https://raw.githubusercontent.com/ivelius/WeatherApp/master/screenshots/screenshot.png?s=100)

## Feature List

  - Supports both orientations [Implemented]
  - Presents weather based on location [Implemented]
  - User is able to refresh the data by clicking a button [Implemented]
  - User is able to see a weekly forecast [TBD]
  - Notifications on weather changes [TBD]

## Tech Stack

  - Fully written in Kotlin
  - Retrofit + RxJava used for API calls
  - Unit tests are partially implemented
  - Only one UI test
  - Using CircleCI to run the tests (see tests status on top of this README file)
  - MVVM Architecture
  - No DI framework was used
  
## Installation
  - Clone the project and open it in Android Studio 3.+
  - Run the project on Emulator / Device with internet connection
  - To run tests I suggest to run them directly from the Android Studio by pressing a "play" button that appears near the test