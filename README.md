# sample-transport-app
[![Build Status](https://travis-ci.org/Zlate87/sample-transport-app.svg?branch=master)](https://travis-ci.org/Zlate87/sample-transport-app)

## Intro
Sample application that showcases displaying of transportation data.
## Developer Guide
### Building
* The applicaiton is using Dagger 2 for dependency injection. Sine Dagger 2 works at compile time, some classes need to be generated. This classes are generated automatiacally and are not commited. Because of this, the project must be build once after it is cloned or Android Studio will show errors (missing degger classes).

* The applicaiton is using Google Maps, and for it to work it needs Maps API key. The API key is commited (see [google_maps_api.xml](sample-transport-app/app/src/main/res/values/google_maps_api.xml)), but it works only if the applicaiton is signed with the signing key that the API key is for. The signing key is not commited (it is a sicret :)). So in order for the maps to work properly you will need to generate a new API key that will be pared to your signing key. More about how to do this [here](https://developers.google.com/maps/documentation/android-api/signup?hl=en).

### Tests
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
