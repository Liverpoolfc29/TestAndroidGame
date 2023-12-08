package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.test_1.R;

public class MainManuScene extends SceneFW {

    public MainManuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void upDate() {

    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        /*
         обращаемся к нашему файлу strings.xml. Каждая сцена работает с ядром и общараясь к ядру должны видеть их.
         B данном месте не важно какой размер экрана, мы все ресуем на фреймбуфер который всегда у нас размером 800 на 600.
         На каждой итерации Лупа фрейм буфер будет менятся и по идее масштабировать под экран смартфона на котором запущено приложение.
        */
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainManu_nameGame), 100, 100, Color.BLUE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainManu_newGame), 20, 300, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainManu_settings), 20, 350, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainManu_results), 20, 400, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainManu_exitGame), 20, 450, Color.BLUE, 40, null);
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
