package com.kidoz.sdk.gdx.sampleapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SampleGame extends ApplicationAdapter
{
    SpriteBatch batch;
    Texture btn;
    Stage stage;
    Image mInterstitialBtn;

    IOnAddControllInterface mInterface;

    public SampleGame(IOnAddControllInterface listener)
    {
        this.mInterface = listener;
    }

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        btn = new Texture(Gdx.files.internal("btn.png"));
        mInterstitialBtn = new Image(btn);

        mInterstitialBtn.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                mInterface.showInterstitial();
                return true;
            }
        });
        mInterstitialBtn.setScale(0.7f);
        mInterstitialBtn.setPosition(20,20);

        stage.addActor(mInterstitialBtn);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 100, 100, 100);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        stage.dispose();
        btn.dispose();
    }

    public interface IOnAddControllInterface
    {
        public void showInterstitial();
        public void openPanel();
        // ... Additional functions you need
    }
}
