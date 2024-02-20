package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;

public class SettingsScene extends SceneGameFW {

    protected SettingsScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
    }

    @Override
    protected void upDate() {

    }

    @Override
    protected void drawing() {
        coreGameFW.getGraphicsFW().clearScene(Color.BLACK);
        coreGameFW.getGraphicsFW().drawText("Settings", 250, 200, Color.GREEN, 50, null);
        coreGameFW.getGraphicsFW().drawText("Music ", 250, 300, Color.GREEN, 30, null);
        coreGameFW.getGraphicsFW().drawText("Sound ", 250, 350, Color.GREEN, 30, null);

        coreGameFW.getGraphicsFW().drawText("On ", 400, 300, Color.GREEN, 30, null);
        coreGameFW.getGraphicsFW().drawText("On ", 400, 350, Color.GREEN, 30, null);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void dispose() {

    }

}