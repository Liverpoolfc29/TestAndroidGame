package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Utillits.SettingsGame;

public class SettingsScene extends SceneGameFW {

    protected SettingsScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
    }

    @Override
    protected void upDate() {
        if (coreGameFW.getIsPressedKeyBack()) {
            coreGameFW.setIsPressedKeyBack(false);
            coreGameFW.setSceneFW(new MainManuSceneGame(coreGameFW));
        }

        if (coreGameFW.getTouchListenerFW().getTouchUp(450, 300 - 10, 80, 50)) {
            /**
             * Ставил смещение координат на слушателя что б лучше попадать в центр слова
             */
            SettingsGame.musicOn = !SettingsGame.musicOn;
            SettingsGame.saveSettings(coreGameFW);
        }

        if (coreGameFW.getTouchListenerFW().getTouchUp(450, 350 - 10, 80, 50)) {
            SettingsGame.soundOn = !SettingsGame.soundOn;
            SettingsGame.saveSettings(coreGameFW);
        }

    }

    @Override
    protected void drawing() {
        coreGameFW.getGraphicsFW().clearScene(Color.BLACK);
        coreGameFW.getGraphicsFW().drawText("Settings", 250, 200, Color.GREEN, 100, null);
        coreGameFW.getGraphicsFW().drawText("Music ", 300, 300, Color.GREEN, 50, null);
        coreGameFW.getGraphicsFW().drawText("Sound ", 300, 350, Color.GREEN, 50, null);

        if (SettingsGame.musicOn) {
            coreGameFW.getGraphicsFW().drawText("On ", 450, 300, Color.GREEN, 30, null);
        } else {
            coreGameFW.getGraphicsFW().drawText("Off ", 450, 300, Color.RED, 30, null);
        }

        if (SettingsGame.soundOn) {
            coreGameFW.getGraphicsFW().drawText("On ", 450, 350, Color.GREEN, 30, null);
        } else {
            coreGameFW.getGraphicsFW().drawText("Off ", 450, 350, Color.RED, 30, null);
        }
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