package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Utillits.ResourceGame;

public class ExitScene extends SceneGameFW {

    protected ExitScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
    }

    @Override
    protected void upDate() {
        /**
         * работаем с выходом из игры
         */
        if (coreGameFW.getTouchListenerFW().getTouchUp(250, 250, 100, 50)) {
            coreGameFW.finish();
        }
        if (coreGameFW.getTouchListenerFW().getTouchUp(250, 300, 100, 50)) {
            coreGameFW.setSceneFW(new MainManuScene(coreGameFW));
        }
    }

    @Override
    protected void drawing() {
        coreGameFW.getGraphicsFW().clearScene(Color.BLACK);
        coreGameFW.getGraphicsFW().drawText("Are you sure", 200, 200, Color.CYAN, 100, ResourceGame.mainMenuFount_1);
        coreGameFW.getGraphicsFW().drawText("Yes", 250, 250, Color.GRAY, 50, ResourceGame.mainMenuFount_1);
        coreGameFW.getGraphicsFW().drawText("No", 250, 300, Color.GRAY, 50, ResourceGame.mainMenuFount_1);
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