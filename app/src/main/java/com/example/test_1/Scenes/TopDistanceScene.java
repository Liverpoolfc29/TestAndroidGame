package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.test_1.R;
import com.example.test_1.Utillits.SettingsGame;

public class TopDistanceScene extends SceneFW {

    private String[] numbers = new String[5];

    protected TopDistanceScene(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i + 1) + " " + SettingsGame.getDistance()[i];
        }
    }

    @Override
    public void upDate() {
        /*
            вешаем слушатель на нажатие, если нажмет на экран значит выходим из сцены
         */
        if (coreFW.getTouchListeneerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            coreFW.setSceneFW(new MainManuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        /*
            выводим на экран все результаты и надпись лучшие результаты результаты
         */
        graphicsFW.drawText(coreFW.getString(R.string.txt_top_distance), 120, 200, Color.GREEN, 40, null);
        graphicsFW.drawText(String.valueOf(numbers[0]), 150, 250, Color.GREEN, 30, null);
        graphicsFW.drawText(String.valueOf(numbers[1]), 150, 300, Color.GREEN, 30, null);
        graphicsFW.drawText(String.valueOf(numbers[2]), 150, 350, Color.GREEN, 30, null);
        graphicsFW.drawText(String.valueOf(numbers[3]), 150, 400, Color.GREEN, 30, null);
        graphicsFW.drawText(String.valueOf(numbers[4]), 150, 450, Color.GREEN, 30, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }

}