package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.R;
import com.example.test_1.Utillits.ResourceGame;

public class MainManuScene extends SceneGameFW {

    public MainManuScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
    }

    @Override
    public void upDate() {
        /*
         апдейтится 60 раз в секунду. На каждой итерации апдейта нужно проверять было ли нажатие на экран по таким то координатам.
         Для этого мы передаем координаты нашей надписи на экране и проверем если было нажатие, если было нажатие (нова игра), задаем новую сцену, и в передаем
         туда сцену создавая новую сцену и передавая туда наше ядро.
        */
        if (coreGameFW.getTouchListenerFW().getTouchUp(20, 300, 100, 50)) {
            ResourceGame.touch.play(2); // запускам звук при нажатии
            coreGameFW.setSceneFW(new GameScene(coreGameFW));
        }
        if (coreGameFW.getTouchListenerFW().getTouchUp(20, 400, 100, 50)) {
            ResourceGame.touch.play(2);
            coreGameFW.setSceneFW(new TopDistanceSceneGame(coreGameFW));
        }

        if (coreGameFW.getTouchListenerFW().getTouchUp(20, 350, 100, 50)) {
            ResourceGame.touch.play(2);
            coreGameFW.setSceneFW(new SettingsScene(coreGameFW));
        }

    }

    @Override
    public void drawing() {
        graphicsGameFW.clearScene(Color.BLACK);
        /*
         обращаемся к нашему файлу strings.xml. Каждая сцена работает с ядром и общараясь к ядру должны видеть их.
         B данном месте не важно какой размер экрана, мы все ресуем на фреймбуфер который всегда у нас размером 800 на 600.
         На каждой итерации Лупа фрейм буфер будет менятся и по идее масштабировать под экран смартфона на котором запущено приложение.
        */
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_mainManu_nameGame), 100, 100, Color.BLUE, 60, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_mainManu_newGame), 20, 300, Color.BLUE, 40, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_mainManu_settings), 20, 350, Color.BLUE, 40, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_mainManu_results), 20, 400, Color.BLUE, 40, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_mainManu_exitGame), 20, 450, Color.BLUE, 40, null);
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
