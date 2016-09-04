<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a><br>
[<img src="https://kidoz-cdn.s3.amazonaws.com/wordpress/kidoz_small.gif" width="533px" height="300px">](https://www.youtube.com/watch?v=-ljFjRn7jeM)


# KIDOZ libGDX Android SETUP

*Updated to KIDOZ SDK version 0.5.9*

For full Documentation of KIDOZ SDK fo Gradle (Android Studio) please take a look at [HERE](https://github.com/Kidoz-SDK/KIDOZ_Android_SDK_Example-Android-Studio)

#### To add KIDOZ SDK to libGDX project add KIDOZ SDK dependency to `build.gradle` file of `android` Module
<img src="https://s3.amazonaws.com/kidoz-cdn/sdk/GitHub_Tutorial_Img/lib_gdx_tut_1.png" width="200px" height="262px">

#### Add KIDOZ SDK dependency in the android {...} section `build.gradle` file 
```groovy

android {

    ...

   dependencies {
        compile 'com.kidoz.sdk:KidozSDK:0.5.9'
   }
}
```

#### Add AndroidMainifest.xml  Definitions (IMPORTANT) to `AndroidMainifest.xml` file in the same `android` Module
For correct flow of the SDK, add the following line in your `AndroidMainifest.xml` file, for each `Activity` that uses the SDK functionality.
```groovy
 android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
``` 

Also add the following permissions:

```xml
 <uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="22"/>
 <!-- android:maxSdkVersion="22" is used to avoid permission handling in Android 6.0 and above  -->  
``` 

###### IMPORTANT: Hardware Acceleration must be turned ON!
```groovy
 <application android:hardwareAccelerated="true">
``` 

Example:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="your.package.name">
    
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="22"/>
    <!-- android:maxSdkVersion="22" is used to avoid permission handling in Android 6.0 and above  -->  

    <application android:hardwareAccelerated="true">
        <activity
            ...
            android:configChanges="screenLayout|screenSize|orientation|keyboardHidden|keyboard"
            ...
          >
	</activity>
	
	 <receiver android:name="com.kidoz.sdk.api.receivers.SdkReceiver" android:enabled="true" >
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_ADDED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>
        ...
    </application>
</manifest>
 
