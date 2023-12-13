package com.example.test_1.Generators;

import com.example.test_1.Objects.Star;

import java.util.ArrayList;

public class generatorBackground {

    int starsPeak = 50;
    public ArrayList<Star> starArrayList = new ArrayList<>(starsPeak);

    public generatorBackground(int sceneWidth, int sceneHeight) {
        /*
         кол. вездочек.
         На каждой итерации пока 50 штук не появятся мы создаем звездочеки.
        */
        for (int i = 0; i < starsPeak; i++) {
            Star star = new Star(sceneWidth, sceneHeight);
            starArrayList.add(star);
        }
    }

    public void updateStar() {
        for (int i = 0; i < starArrayList.size(); i++) {
            starArrayList.get(i).updateStar();
        }
    }

    public void drawingStar() {

    }

}