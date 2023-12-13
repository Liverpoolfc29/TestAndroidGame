package com.example.test_1.Objects;

import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.speed = 2;
        /*
         Mесто для появления на экране генерируем случайным образом(по высоте и ширине),
         что бы все не появлялось в одном месте.
        */
        this.x = UtilRandomFW.getRandomNumber(maxScreenX);
        this.y = UtilRandomFW.getRandomNumber(maxScreenY);
    }

    public void updateStar() {
        /*
         как только по Х появилась звездочка она должна двигаться в левую часть экрана, поэтому мы
         должны уменьшать ее координату на скорость ее движения
        */
        x -= speed;
        /*
         если звездочка прошла весь экран до края мы должн вернуть ее назад.
         и снова устанавливаем ее положение заново.
        */
        if (x < 0) {
            x = maxScreenX;
            y = UtilRandomFW.getRandomNumber(maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}