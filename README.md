# AirLib-Apps-Interview-Test
An Android app that a user can login (No Vadidation), User is greeted based on the current time, list of medicine is shown below. AirLibs Interview Test

## Prerequisites
To run the project in your local environment, you need
* Go to the android studio and create a new project using the 'Get from version control' option.
* Paste this link `https://github.com/jlutukai/AirLib-Apps-Interview-Test.git`
* Add `https://run.mocky.io`  to `local.properties` file  as BASE_URL
* Build the project and run it

## Tech-stack
Tech-stack
* [Kotlin](https://kotlinlang.org/) - a modern, cross-platform, statically typed, general-purpose programming language with type inference.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight threads to perform asynchronous tasks.
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - a stream of data that emits multiple values sequentially.
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state%2Dholder,property%20of%20the%20MutableStateFlow%20class.) - Flow APIs that enable flows to emit updated state and emit values to multiple consumers optimally.
* [Dagger Hilt](https://dagger.dev/hilt/) - a dependency injection library for Android built on top of [Dagger](https://dagger.dev/) that reduces the boilerplate of doing manual injection.
* [Gson](https://github.com/google/gson) A Java and Kotlin serialization/deserialization library to convert Kotlin/Java Objects into JSON and back
* [Jetpack](https://developer.android.com/jetpack)
    * [Jetpack Compose](https://developer.android.com/jetpack/compose) - A modern toolkit for building native Android UI
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle state.
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data lifecycle in a conscious manner and survive configuration change.
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - A data storage solution backed using Kotlin Coroutines and Flows that allows you to store key-value pairs or typed objects with protocol buffers
    * [RoomDB](https://developer.android.com/training/data-storage/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite

#### Testing
Unit tests that the data layer

<img src="/screenShots/api_call_mock.jpeg"/>

Unit tests testing app layer

<img src="/screenShots/greeting_test.jpeg"/>

### App Architecture
A well planned architecture is extremely important for an app to scale and all architectures have one common goal- to manage complexity of your app. This isn't something to be worried about in smaller apps however it may prove very useful when working on apps with longer development lifecycle and a bigger team.

Read more about clean architecture [here](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) <br />
The app uses clean architecture with the following modules
#### 1. Domain
This is the core layer of the application. The domain layer is independent of any other layers thus ] domain models and business logic can be independent from other layers.This means that changes in other layers will have no effect on domain layer eg. screen UI (presentation layer) or changing database (data layer) will not result in any code change withing domain layer.
Components of domain layer
<br/>
* Models: Defines the core structure of the data that will be used within the application.

* Repositories: Interfaces used by the use cases. Implemented in the data layer.

#### 2. Data 
The data layer is responsibile for selecting the proper data source for the domain layer (In this case it contains only local source). It contains the implementations of the repositories declared in the domain layer.
* Repositories: Responsible for exposing data to the domain layer.

* Mappers: They perform data transformation between domain, dto and entity models.
* Sources: Responsible for deciding which data source (network or cache) will be used when fetching data.

#### 3. Presentation(app)
The presentation layer contains components involved in rendering information to the user. The main part of this layer are the views(Fragment, Activities, Composables) and viewModels.

#### App Screenshots
| Splash               | Login            |
| -------------         |:--------------------: | 
| <img src="/screenShots/splash.jpeg" width="260">    | <img src="/screenShots/login.jpeg" width="260">     | 

| Medicine List     | View One Medicine        |
| :-----------------:| :------------------------:|
| <img src="/screenShots/drugs_list.jpeg" width="260">  | <img src="/screenShots/drug_detail.jpeg" width="260">         |

