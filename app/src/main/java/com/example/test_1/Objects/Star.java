package com.example.test_1.Objects;

import com.example.myframework.ObjectGameFW;
import com.example.myframework.Utilits.UtilRandomFW;

public class Star extends ObjectGameFW {

    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 2;
        /*
         Mесто для появления на экране генерируем случайным образом(по высоте и ширине),
         что бы все не появлялось в одном месте.
         по У генерируем звезды до верхнего предела екрана, до HUD, используем метод для рандома между двумя числами
        */
        this.x = UtilRandomFW.getRandomNumber(maxScreenX);
        this.y = UtilRandomFW.getGap(minScreenY, maxScreenY);
    }

    public void updateStar(double speedPlayer) {
        /*
         как только по Х появилась звездочка она должна двигаться в левую часть экрана, поэтому мы
         должны уменьшать ее координату на скорость ее движения
        */
        x -= speed;
        x -= speedPlayer;
        /*
         если звездочка прошла весь экран до края мы должн вернуть ее назад.
         и снова устанавливаем ее положение заново.
        */
        if (x < 0) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}