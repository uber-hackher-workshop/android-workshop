# android-workshop
This is a simple Android sample app written in Kotlin and using Android Jetpack, ViewModels, Hilt, Retrofit, OkHttp, and RxRelays.

# Overview
The app follows the following architecture:

![Image of App Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png) 

The repository is responsible for orchestrating and retrieving data. The repository can be a mediator between multiple data sources (i.e. different APIs, databases, etc...). The purpose of this is to abstract away data sources from the rest of the app. The sample app has a "NewsRepository". 

The ViewModels provide data for a specific UI component and contains data-handling business logic if necessary. The ViewModel doesn't know about UI components. The sample app has two ViewModels: "NewsArticleViewModel" and "SearchViewModel".

An activity is a single, focused thing that the user can do. Almost all activities interact with the user and handle the lifecycle events of an app. The sample app has a single activity called "MainActivity".

Fragments live inside activities and each activity can host many fragments. Fragments can be thought of as a modular section of an activity. The sample app has three fragments: "NewsFragment", "SearchFragment", and "SocialFragment". The MainActivity has a bottom nav bar that is responsible for activating and deactivating the associated fragments.