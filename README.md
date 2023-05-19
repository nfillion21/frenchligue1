# frenchligue1

A Jetpack compose app using thesportsdb API.

Libraries and components used
--------------
* [Coroutine Worker][0] - Used to load data and fill database asynchronously.
* [Room][1] - Access the app's SQLite database with in-app objects and compile-time checks.
* [Preferences DataStore][2] - Used to save preferences asynchronously thanks to Kotlin flows.
* [Hilt][3] for [dependency injection][4]
* [Coil][5] - Used to load images from network.
* [Ktor][6] - Ktor is built from the ground up using Kotlin and Coroutines. Very lightweight and promising library built by JetBrains.
* [Kotlin flow][7] - Kotlin flow is an asynchronous stream library built on top of Kotlin coroutines.

[0]: https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker
[1]: https://developer.android.com/topic/libraries/architecture/room
[2]: https://developer.android.com/topic/libraries/architecture/datastore
[3]: https://developer.android.com/training/dependency-injection/hilt-android
[4]: https://developer.android.com/training/dependency-injection
[5]: https://coil-kt.github.io/coil/compose/
[6]: https://ktor.io/
[7]: https://developer.android.com/kotlin/flow
