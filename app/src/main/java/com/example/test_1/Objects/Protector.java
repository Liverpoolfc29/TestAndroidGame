package com.example.test_1.Objects;

import android.graphics.Rect;

import com.example.myframework.AnimationFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilRandomFW;
import com.example.test_1.Utillits.UtilResource;
/*
    Класс для отрисовки и генерации протектора на экране, пофвился прошел по траэктории и все
 */
public class Protector extends ObjectFW {

    AnimationFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        /*
        максимальное значение минус высота,что бы астероиды не летели ниже экрана.
         */
        this.maxScreenY = maxScreenY - UtilResource.spriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        /*
        Устанавливаем первоначальное положение астероидам на экране
         */
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResource.spritePlayer.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                UtilResource.spriteProtector.get(0).getWidth(),
                UtilResource.spriteProtector.get(0).getHeight());
    }

    public void update(double speedPlayer) {
            x -= speed;
            x -= speedPlayer;

        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animProtector.runAnimation();

        hitBox = new Rect(x, y,
                UtilResource.spriteEnemy.get(0).getWidth(),
                UtilResource.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animProtector.drawingAnimation(graphicsFW, x, y);
    }

}