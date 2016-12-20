package com.kidoz.sdk.gdx.sampleapp.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.kidoz.sdk.gdx.sampleapp.SampleGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SampleGame(new SampleGame.IOnAddControllInterface()
                {
                        @Override
                        public void showInterstitial()
                        {

                        }

                        @Override
                        public void openPanel()
                        {

                        }
                });
        }
}