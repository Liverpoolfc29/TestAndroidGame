package com.example.test_1.Objects;

import com.example.myframework.ObjectFW;
import com.example.test_1.Classes.AnimationGame;

/*
    Каждый обьект и наш игрок должен знать габариты экрана (максимальную высоту и ширину) что бы не вылетать за его пределы.
 */
public class MainPlayer extends ObjectFW {

    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    AnimationGame animationSpriteMainPlayer;

    public MainPlayer(int maxScreenX, int maxScreenY, int minScreenY) {
        /*
            обозначим место появления и начальную скорость.
         */
        x = 20;
        y = 200;
        speed = 1;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY;
    }
}
