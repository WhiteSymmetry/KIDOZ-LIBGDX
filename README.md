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
```
 
#### Example of adding a widget to activity
```groovy
   public class AndroidLauncher extends AndroidApplication {
   
    // Add  and game container
    private RelativeLayout mContainer;
    // Kidoz Interstitial instance
    private KidozInterstitial mInterstitial;
    // Kidoz PAnelView instance
    private PanelView mPanelView;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create the layout container
        mContainer = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new YourGame( ), config);
        // Add the libgdx view
        mContainer.addView(gameView);

        // Initialize KIDOZ SDK 
        KidozSDK.initialize(getContext(), ..publisheID.., ..securityToke..);
         
        // Initialize KIDOZ PanelView widget
        initPanelViewAd();
        // Initialize KIDOZ Interstitial
        initInterstitialAd();
        
        // Hook it all up
        setContentView(mContainer);
     }
   
   /** Initialize KIDOZ PanelView widget */
   private void initPanelViewAd() {
     mPanelView = new PanelView(this);
        mPanelView.setPanelConfiguration(PANEL_TYPE.TOP, HANDLE_POSITION.END);
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener() {
            @Override
            public void onPanelViewCollapsed() {
            }

            @Override
            public void onPanelViewExpanded() {
            }
            
            @Override
            public void onPanelReady() {
            }
        });
      
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContainer.addView(mPanelView, adParams);
   }

  /** Initialize KIDOZ Interstitial */
  private void initInterstitialAd()
  {
        mKidozInterstitial = new KidozInterstitial(this);
            mKidozInterstitial.setOnInterstitialEventListener(new BaseInterstitial.IOnInterstitialEventListener()
            {
                @Override
                public void onClosed()
                {
                }

                @Override
                public void onOpened()
                {
                }

                @Override
                public void onReady()
                {
                    mKidozInterstitial.show();
                }

                @Override
                 public void onLoadFailed()
                {
                }
            });
  }
   
``` 

### How to control the KIDOZ SDK `Widget`s and `View` from your Game
 
 In the class that Extends Game create an `Interface` to comunicate with `AndroidLAuncher` class
 
```groovy
public class YourGame extends Game {

   IOnAddControllInterface mInterface;
   
   public YourGame (IOnAddControllInterface interface) {
   	this.mInterface = interface
   
        ...
   }
   
   ...
   
   public interface IOnAddControllInterface {
       public void showInterstitial()
       public void openPanel()
       
       ... // Additional functions you need
   }
}

```

 Let extended `AndroidLAuncher` class to implement the `interface` and pass the instance in the constractor of `YourGame`
 also create a `Handler` that will pass the calls to UI Thread.
 That way is posible to controll Kidoz SDK ads from the GAME.
 
 ```groovy
 public class AndroidLauncher extends AndroidApplication implements YourGame.IOnAddControllInterface {
      
        private final int SHOW_INTERSTITIAL_AD = 1;
        private final int OPEN_PANEL_AD = 2;
      
        protected Handler handler = new Handler() {
        @Override
	public void handleMessage(Message msg) {
	          switch (msg.what) {
	               case SHOW_INTERSTITIAL_AD: {
	                    mKidozInterstitial.loadAd();
	                    break;
	               }
	               case OPEN_PANEL_AD: {
	                    mPanelView.expandPanelView();
	                    break;
	               }
	           }
	       }
	  };
 
 
 	@Override
        protected void onCreate(Bundle savedInstanceState) {
         ...
         
         View gameView = initializeForView(new YourGame(this), config);
         
         ...
        }
        
         @Override
        public void showInterstitial() {
             handler.sendEmptyMessage(SHOW_INTERSTITIAL_AD);
        }

    	@Override
    	public void openPanel() {
              handler.sendEmptyMessage(OPEN_PANEL_AD);
    	}
 }
 
```

For any question or assistance, please contact us at SDK@kidoz.net.
</br>

License
--------

    Copyright 2015 KIDOZ, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
