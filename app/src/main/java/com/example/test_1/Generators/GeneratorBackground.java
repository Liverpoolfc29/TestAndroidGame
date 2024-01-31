package com.example.test_1.Generators;

import android.graphics.Color;

import com.example.myframework.GraphicsFW;
import com.example.test_1.Objects.Star;

import java.util.ArrayList;

public class GeneratorBackground {
    int starsPeak = 50; //Максимальное количество звезд
    private ArrayList<Star> starArrayList = new ArrayList<>(starsPeak);

    public GeneratorBackground(int sceneWidth, int sceneHeight, int minScreenY) {
        /*
         кол. вездочек.
         На каждой итерации пока 50 штук не появятся мы создаем звездочеки.
        */
        for (int i = 0; i < starsPeak; i++) {
            //Цикл создает звезды и помещет их в массив
            Star star = new Star(sceneWidth, sceneHeight, minScreenY);
            starArrayList.add(star);
        }
    }

    public void updateStar(double speedPlayer) {
        //Производим обновление каждой звезды в массиве
        for (int i = 0; i < starArrayList.size(); i++) {
            starArrayList.get(i).updateStar(speedPlayer);
        }
    }

    public void drawingStar(GraphicsFW graphicsFW) {
        /*
         отрисовываем звездочки, берем по одной и рисуем ее на той координате которая у нее была задана в момент ее создания,
         по высоте и ширине.
        */
        for (int i = 0; i < starArrayList.size(); i++) {
            graphicsFW.drawPixel(starArrayList.get(i).getX(), starArrayList.get(i).getY(), Color.WHITE);
        }
    }

}