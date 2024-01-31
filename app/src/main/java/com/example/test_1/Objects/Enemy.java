package com.example.test_1.Objects;

import android.graphics.Rect;

import com.example.myframework.AnimationFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilRandomFW;
import com.example.test_1.Utillits.UtilResource;

public class Enemy extends ObjectFW {

    private AnimationFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnemy(enemyType);
    }

    private void initTypeEnemy(int enemyType) {
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(1, 6);
                animEnemy = new AnimationFW(3,
                        UtilResource.spriteEnemy.get(0),
                        UtilResource.spriteEnemy.get(1),
                        UtilResource.spriteEnemy.get(2),
                        UtilResource.spriteEnemy.get(3));
                break;
            case 2:
                speed = UtilRandomFW.getGap(4, 9);
                break;
        }
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        /*
        максимальное значение минус высота,что бы астероиды не летели ниже экрана.
         */
        this.maxScreenY = maxScreenY - UtilResource.spriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        /*
        Устанавливаем первоначальное положение астероидам на экране
         */
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResource.spriteEnemy.get(0).getWidth() / 2;
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animEnemy.runAnimation();
        hitBox = new Rect(x, y,
                UtilResource.spriteEnemy.get(0).getWidth(),
                UtilResource.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animEnemy.drawingAnimation(graphicsFW, x, y);
    }

}