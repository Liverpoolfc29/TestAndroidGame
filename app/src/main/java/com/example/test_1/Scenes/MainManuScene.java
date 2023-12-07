package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;

public class MainManuScene extends SceneFW {

    public MainManuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void upDate() {

    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.GREEN);
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
