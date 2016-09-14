package com.kidoz.sdk.gdx.sampleapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kidoz.sdk.api.FeedButton;
import com.kidoz.sdk.api.FlexiView;
import com.kidoz.sdk.api.KidozInterstitial;
import com.kidoz.sdk.api.KidozSDK;
import com.kidoz.sdk.api.PanelView;
import com.kidoz.sdk.api.interfaces.FlexiViewListener;
import com.kidoz.sdk.api.interfaces.IOnPanelViewEventListener;
import com.kidoz.sdk.api.ui_views.flexi_view.FLEXI_POSITION;
import com.kidoz.sdk.api.ui_views.interstitial.BaseInterstitial;
import com.kidoz.sdk.api.ui_views.panel_view.HANDLE_POSITION;
import com.kidoz.sdk.api.ui_views.panel_view.PANEL_TYPE;

public class AndroidLauncher extends AndroidApplication implements SampleGame.IOnAddControllInterface
{

    // Add  and game container
    private RelativeLayout mContainer;
    // KIDOZ Interstitial instance
    private KidozInterstitial mKidozInterstitial;
    // KIDOZ PanelView instance
    private PanelView mPanelView;
    // KIDOZ FeedButton View
    private FeedButton mFeedButton;
    // KIDOZ FlexiPoint View
    private FlexiView mFlexiView;

    private final int SHOW_INTERSTITIAL_AD = 1;

    // Execution Handler that execute calls on UI thread
    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case SHOW_INTERSTITIAL_AD:
                {
                    mKidozInterstitial.loadAd();
                    break;
                }
                //....
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        // Create the layout container
        mContainer = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the libgdx View
        View gameView = initializeForView(new SampleGame(this), config);
        // Add the libgdx view
        mContainer.addView(gameView);

        // Initialize KIDOZ SDK
        KidozSDK.initialize(this, "5", "i0tnrdwdtq0dm36cqcpg6uyuwupkj76s");

        // Initialize KIDOZ PanelView widget
        initPanelViewAd();

        // Initialize KIDOZ Interstitial
        initInterstitialAd();

        /** Initiate and add Feed Button view */
        initFeedButtonView();

        /** Initiate and add Flexi view */
        initFlexiPointView();

        // Hook it all up
        setContentView(mContainer);
    }

    /**
     * Initiate and add Feed Button view
     */
    private void initFeedButtonView()
    {
        // Create KIDOZ Feed Button instance
        mFeedButton = new FeedButton(this);

        // Add button to container and place it as desired
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mContainer.addView(mFeedButton, params);
    }

    /**
     * Initiate and add Flexi view
     */
    private void initFlexiPointView()
    {
        mFlexiView = (new FlexiView(this));
        mFlexiView.setAutoShow(true);
        mFlexiView.setFlexiViewInitialPosition(FLEXI_POSITION.MIDDLE_CENTER);
        mFlexiView.setOnFlexiViewEventListener(new FlexiViewListener()
        {
            @Override
            public void onViewReady()
            {
                super.onViewReady();
                Toast.makeText(AndroidLauncher.this, "Flexi Ready",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewHidden()
            {
                super.onViewHidden();
                Toast.makeText(AndroidLauncher.this, "Flexi Hidden",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewVisible()
            {
                super.onViewVisible();
                Toast.makeText(AndroidLauncher.this, "Flexi Visible",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Add flexi view to container
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContainer.addView(mFlexiView, params);
    }

    // Initialize KIDOZ Interstitial
    private void initInterstitialAd()
    {
        mKidozInterstitial = new KidozInterstitial(this);
        mKidozInterstitial.setOnInterstitialEventListener(new BaseInterstitial.IOnInterstitialEventListener()
        {
            @Override
            public void onClosed()
            {
                Toast.makeText(AndroidLauncher.this, "Interstitial Closed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpened()
            {
                Toast.makeText(AndroidLauncher.this, "Interstitial Opened",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReady()
            {
                Toast.makeText(AndroidLauncher.this, "Interstitial Ready!",
                        Toast.LENGTH_SHORT).show();
                mKidozInterstitial.show();
            }

            @Override
            public void onLoadFailed()
            {
                Toast.makeText(AndroidLauncher.this, "Interstitial Load failed!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Initialize KIDOZ PanelView widget
    private void initPanelViewAd()
    {
        mPanelView = new PanelView(this);
        mPanelView.setPanelConfiguration(PANEL_TYPE.BOTTOM, HANDLE_POSITION.END);
        mPanelView.setOnPanelViewEventListener(new IOnPanelViewEventListener()
        {
            @Override
            public void onPanelViewCollapsed()
            {
                /** Panel View Collapsed by user or action */
                Toast.makeText(AndroidLauncher.this, "PanelView Collapsed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelViewExpanded()
            {
                /** Panel View Expanded by user or action */
                Toast.makeText(AndroidLauncher.this, "PanelView Expanded",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelReady()
            {
                Toast.makeText(AndroidLauncher.this, "PanelView View Ready",
                        Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContainer.addView(mPanelView, adParams);
    }

    @Override
    public void showInterstitial()
    {
        // Sents call to show interstitial, executed on UI thread
        handler.sendEmptyMessage(SHOW_INTERSTITIAL_AD);
    }
}
