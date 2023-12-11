package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;

public class GameScene extends SceneFW {


    public GameScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void upDate() {

    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText("Game Scene Over Here", 100, 150, Color.GREEN, 60, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
