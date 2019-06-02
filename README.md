# Clean-MVVM-NewsApp

> An Android application built using Clean + MVVM architecture.

## Components used in the app.
- Kotlin
- LiveData
- RxKotlin
- ViewModel
- Retrofit
- Koin
- Room database

## Description
NewsApp is a simple app which uses [News API](https://newsapi.org/) to show the latest news from Google news. This app is built using Clean + MVVM architecture, uses [Retrofit2](http://square.github.io/retrofit/) for making API calls and uses [Room](https://developer.android.com/topic/libraries/architecture/room.html) database to cache the data.

## Architecture
![Architecture](https://github.com/Naveentp/Clean-MVVM-NewsApp/blob/master/ART/clean_mvvm.jpeg)

## Screenshots
<img alt="NewsApp" height="450px" src="https://github.com/Naveentp/Clean-MVVM-NewsApp/blob/master/ART/Screenshot-1.png" />

## Steps to build the app
- Create your API key at [News API](https://newsapi.org/)
- Add the generated API key in `app/build.gradle` like below  
`buildConfigField "String", "NEWS_API_KEY", '"YOUR_NEWS_API_KEY"'`
- Build and run the app.