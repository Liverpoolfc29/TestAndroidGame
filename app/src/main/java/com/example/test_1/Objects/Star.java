package com.example.test_1.Objects;

import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.speed = 4;
        /*
         Mесто для появления на экране генерируем случайным образом(по высоте),
         что бы все не появлялось в одном месте, в правой стороне экрана.
        */
        this.x = UtilRandomFW.getRandomNumber(maxScreenX);
        this.y = maxScreenY;
    }
}
