# Rocket
A SharedPreferences library for Android , to speed up development

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
implementation 'com.github.stavro96:Rocket:1.0'
```

## Usage

Just call the rocket instance like : 

```Kotlin
private val rocket = Rocket.launch(mContext, SHARED_PREFERENCES_FILE_NAME)
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

> Note : the current version has only the default private mode for SharedPrefs .
