<a href="url"><img src="https://github.com/Kidoz-SDK/Kidoz_Android_SDK_Example/blob/master/graphics/App%20icon.png" align="left" height="72" width="72" ></a><br>
[<img src="https://kidoz-cdn.s3.amazonaws.com/wordpress/kidoz_small.gif" width="533px" height="300px">](https://www.youtube.com/watch?v=-ljFjRn7jeM)

# KIDOZ GMS Android Extension + Example
=================================
**KIDOZ SDK Android Extension For Game Maker Studio, compatible with Android 4.0 (API level 14) and above.** 

*Updated to KIDOZ SDK version 0.5.9*
 
Inludes GMS KIDOZ SDK Android Extension library + Sample project and example of the [KIDOZ](http://www.kidoz.net) SDK integration for Game Maker Studio android extension.

The example application contains the following creative tools:
* KIDOZ Feed view (+Family) content tool - the `FeedView`
* KIDOZ Panel view (+Family) content tool - the `PanelView`
* KIDOZ Flexi Point view content tool - the `FlexiView`
* KIDOZ Interstitial View content tool - the `KidozInterstitial`


To avoid compilation error, the folowing lines need to be added to GMS runner project build.gradle file (via ...\runner\ProjectFiles\build.gradle)

```groovy
android {
  ...

  lintOptions {
        abortOnError false
        warning 'InvalidPackage'
  }
    
  ...
    
 }
``` 
