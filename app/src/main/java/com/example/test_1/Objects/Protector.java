package com.example.test_1.Objects;

import android.graphics.Rect;

import com.example.myframework.AnimationGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.myframework.ObjectGameFW;
import com.example.myframework.Utilits.UtilRandomFW;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.Utillits.ResourceGame;

/*
    Класс для отрисовки и генерации протектора на экране, пофвился прошел по траэктории и все
 */
public class Protector extends ObjectGameFW {

    private AnimationGameFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        /*
        максимальное значение минус высота,что бы астероиды не летели ниже экрана.
         */
        this.maxScreenY = maxScreenY - ResourceGame.spriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        /*
            Устанавливаем первоначальное положение астероидам на экране
         */
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = ResourceGame.spritePlayer.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                ResourceGame.spriteProtector.get(0).getWidth(),
                ResourceGame.spriteProtector.get(0).getHeight());
        animProtector = new AnimationGameFW(GameManager.SPEED_ANIMATION,
                ResourceGame.spriteProtector.get(0),
                ResourceGame.spriteProtector.get(1),
                ResourceGame.spriteProtector.get(2),
                ResourceGame.spriteProtector.get(3));
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;

        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animProtector.runAnimation();
        hitBox = new Rect(x, y,
                ResourceGame.spriteEnemy.get(0).getWidth(),
                ResourceGame.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsGameFW graphicsGameFW) {
        animProtector.drawingAnimation(graphicsGameFW, x, y);
    }
}