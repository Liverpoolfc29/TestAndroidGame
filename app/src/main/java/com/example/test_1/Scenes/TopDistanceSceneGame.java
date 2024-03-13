package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.R;
import com.example.test_1.Utillits.SettingsGame;

public class TopDistanceSceneGame extends SceneGameFW {

    private String[] numbers = new String[5];

    protected TopDistanceSceneGame(CoreGameFW coreGameFW) {
        super(coreGameFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i + 1) + " " + SettingsGame.getDistance()[i];
        }
    }

    @Override
    public void upDate() {
        /**
         * вешаем слушатель на нажатие, если нажмет на экран значит выходим из сцены
         */
        if (coreGameFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            coreGameFW.setSceneFW(new MainManuScene(coreGameFW));
        }
    }

    @Override
    public void drawing() {
        /**
         * выводим на экран все результаты и надпись лучшие результаты результаты
         */
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_top_distance), 120, 200, Color.GREEN, 40, null);
        graphicsGameFW.drawText(String.valueOf(numbers[0]), 150, 250, Color.GREEN, 30, null);
        graphicsGameFW.drawText(String.valueOf(numbers[1]), 150, 300, Color.GREEN, 30, null);
        graphicsGameFW.drawText(String.valueOf(numbers[2]), 150, 350, Color.GREEN, 30, null);
        graphicsGameFW.drawText(String.valueOf(numbers[3]), 150, 400, Color.GREEN, 30, null);
        graphicsGameFW.drawText(String.valueOf(numbers[4]), 150, 450, Color.GREEN, 30, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsGameFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }

}