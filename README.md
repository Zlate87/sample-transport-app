# sample-transport-app
[![Build Status](https://travis-ci.org/Zlate87/sample-transport-app.svg?branch=master)](https://travis-ci.org/Zlate87/sample-transport-app)

## Intro
Sample application that showcases displaying of transportation data.

It shows:
* Arhitecture base practises.
* How to use RX java for long running tasks instead of AsyncTask.
* How to work with JSON data.
* How to handle models not appropriate for the views by introducing view models
* How to do simple data manipulation with joda-time.
* Have compile time dependency injection with Dagger 2.
* How to do JUnit tests with robolectric, mockito, how to mock final classes with powermock.
* How to do UI tests with Espresso
* Working with flavours
* ...

It dosen't show:
* As you can see from the screenshots, a fancy UI.
* Animations 
* Reading data from backend
* ...

## Developer Guide
The applicaiton has two flawours:
* dummyData - It contains flavour specific routing service that will always return a dummy route from JSON file that is pre-bundled with the applicaiton. This flawor allows testing independed of a server
* production - It is intended to have specific routing service that will retreive data from a server for given routing parameters. However at the moment the routing service is not implemented and it thorws a RuntimeException and crashes the applicaiton.
 

### Building
* The applicaiton is using Dagger 2 for dependency injection. Sine Dagger 2 works at compile time, some classes need to be generated. This classes are generated automatiacally and are not commited. Because of this, the project must be build once after it is cloned or Android Studio will show errors (missing degger classes).

* The applicaiton is using Google Maps, and for it to work it needs Maps API key. The API key is commited (see [google_maps_api.xml](sample-transport-app/app/src/main/res/values/google_maps_api.xml)), but it works only if the applicaiton is signed with the signing key that the API key is for. The signing key is not commited (it is a sicret :)). So in order for the maps to work properly you will need to generate a new API key that will be pared to your signing key. More about how to do this [here](https://developers.google.com/maps/documentation/android-api/signup?hl=en).

### Tests
The applicaiton has JUnit tests that focuse on testing the services and Espresso test that focuse on testing the activities and UI elements by mocking the services. At the moment there are no end-to-end tests.

#### JUnit tests
The application comes with robolectric tests (more information about robolectric [here](http://robolectric.org/)). To run the robolectric tests, on the command line run:
```
gradlew test
```
#### Espresso tests
The application comes with espresso tests (more information about espresso [here](https://google.github.io/android-testing-support-library/docs/espresso/index.html)). To run the tests from gradle, on the command line run:
```
gradlew connectedAndroidTest
```
NOTE - when running the espresso test, make sure that: 
* The animations are disabled on the device where the tests are running. More info [here](https://google.github.io/android-testing-support-library/docs/espresso/setup/index.html#running-tests).
* If running the tests on Android 6.0 or up, the ACCESS_FINE_LOCATION permission is granted. This can be done manually of using adb command:
```
 adb shell pm grant "com.zlate87.sample_transport_app.[FLAVOR_NAME]" android.permission.android.permission.ACCESS_FINE_LOCATION
 ```

## Third party libraries
## Next Steps
For the next steps see the list of issues [here] (sample-transport-app/issues).
