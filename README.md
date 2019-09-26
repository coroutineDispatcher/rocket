# Rocket :rocket: :rocket: :rocket:        <a href="https://www.buymeacoffee.com/pnRYZ5qBy" target="_blank"><img src="https://bmc-cdn.nyc3.digitaloceanspaces.com/BMC-button-images/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>
A SharedPreferences library for Android , to speed up development

## KotlinDoc: https://coroutinedispatcher.github.io/rocket_docs/

# Download 

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```Groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
### Step 2. Add the dependency

```Groovy
implementation 'com.github.stavro96:Rocket:1.3.1'
```

## Usage


### Note: New version of Rocket now works only with coroutine support therefore you must include suspend implementation and run it inside a CoroutineScope. Example: 

```Kotlin
suspend fun writeData(data: String){
 rocketInstance.writeString(DATA_KEY, data)
}
```

When launching the method inside a coroutine there is no need to specify the `Dispatchers.IO` dispatcher. It's handled by the library.

Just call the rocket instance like : 

```Kotlin
private val rocket = Rocket.launch(mContext, SHARED_PREFERENCES_FILE_NAME , Context.MODE_PRIVATE)
```

### Saving a value : 

```Kotlin
rocket.writeString(YOUR_DESIRED_KEY_NAME , YOUR_DESIRED_VALUE)
```
### Reading a value : 

```Kotlin
rocket.readString(YOUR_DESIRED_KEY_NAME , YOUR_DESIRED_VALUE)
```

### Deleting all saved SharedPreferences : 

```Kotlin
rocket.crash()
```
### Deleting one particular value :

```Kotlin
rocket.drop(YOUR_DESIRED_KEY)
```


Note : The Mode is by default `Context.MODE_PRIVATE` , if you want to specify different mode , apply it as a third parameter in the `launch()` method

## Extension functions: 

```Kotlin
SOME_INT_KEY.isDefaultInt(rocketInstance)
```

## Licence

```
Copyright 2019 stavro96.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
