package com.example.test_1.Objects;

import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilResource;
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
        /*
        у картинки левый верхний угол точка отсчета поэтому нужно подрезать ораничение на размер картинкии (-64)
         */
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
        /*
        загрузили 4 спрайта из нашего масива  картинок с анимацией игрока
         */
        animationSpriteMainPlayer = new AnimationGame(speed, UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));
    }

    public void update() {
        y -= speed + GRAVITY;

        /*\
        проверка вылетов за экран
         */
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
        }
        animationSpriteMainPlayer.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        animationSpriteMainPlayer.graphicAnimation(graphicsFW, x, y);
    }

}