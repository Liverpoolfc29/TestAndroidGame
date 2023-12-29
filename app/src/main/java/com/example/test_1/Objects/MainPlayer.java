package com.example.test_1.Objects;

import android.graphics.Rect;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.Utilits.UtilTimerDelay;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.Utillits.UtilResource;
import com.example.myframework.AnimationFW;

/*
    Каждый обьект и наш игрок должен знать габариты экрана (максимальную высоту и ширину) что бы не вылетать за его пределы.
 */
public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    CoreFW coreFW;
    /*
        для анимации игрока в обычном состоянии
     */
    AnimationFW animMainPlayer;
    /*
        для анимации игрока в режиме ускорения
     */
    AnimationFW animMainPlayerBoost;
    AnimationFW animExplosionPlayer;
    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;
    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        /*
            обозначим место появления и начальную скорость.
         */
        x = 20;
        y = 200;
        speed = 3;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;
        /*
            вычисляем радиус фигурки, размер фигурки 64 на 64 пикселя, радиус нужен от ее центра координат в середине фигурки, значит берем половину ее
            размера 64\2 и получаем радиус от центра 32
        */
        radius = (double) UtilResource.spritePlayer.get(0).getHeight() / 4;
        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();
        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        /*
            у картинки левый верхний угол точка отсчета поэтому нужно подрезать ораничение на размер картинкии (-64)
         */
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        /*
            загрузили 4 спрайта из нашего масива  картинок с анимацией игрока
         */
        animMainPlayer = new AnimationFW(speed, UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));
        /*
            создаем новую анимацию для игрока в режиме ускорения (подгружаем другие картинки для этого)
        */
        animMainPlayerBoost = new AnimationFW(speed, UtilResource.spritePlayerBoost.get(0),
                UtilResource.spritePlayerBoost.get(1),
                UtilResource.spritePlayerBoost.get(2),
                UtilResource.spritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationFW(speed, UtilResource.spriteExplosionPlayer.get(0),
                UtilResource.spriteExplosionPlayer.get(1),
                UtilResource.spriteExplosionPlayer.get(2),
                UtilResource.spriteExplosionPlayer.get(3));
    }

    public void update() {
        /*
            проверяем на нажатие. Если нажал на любую область экрана включаем буст, если отпустил выключаем. (в параметры метода передаем весь размер экрана)
         */
        if (coreFW.getTouchListeneerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (coreFW.getTouchListeneerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (boosting) {
            speed += 1;
        } else {
            speed -= 3;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;
        /*
        проверка вылетов за экран
         */
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
        }

        if (boosting) {
            animMainPlayerBoost.runAnimation();
        } else {
            animMainPlayer.runAnimation();
        }

        hitBox = new Rect(x, y,
                UtilResource.spritePlayer.get(0).getWidth(),
                UtilResource.spritePlayer.get(0).getHeight());
        if (isGameOver) {
            animExplosionPlayer.runAnimation();
        }
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        /*
            делаем переключение анимации и обычного игрока на игрока в ускорее с помощью бул переменной
         */
        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                } else {
                    animMainPlayer.drawingAnimation(graphicsFW, x, y);
                }
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, x, y);
                if (timerOnShieldHit.timerDelay(0.2)) {
                    hitEnemy = false;
                } else {
                    hitEnemy = true;
                }
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW, x, y);
            if (timerOnGameOver.timerDelay(0.5)) {
                GameManager.gameOver = true;
            }
        }
    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldPlayer() {
        return shieldsPlayer;
    }

    public void hitEnemy() {
        shieldsPlayer--;
        if (shieldsPlayer < 0) {
            isGameOver = true;
            timerOnGameOver.startTimer();
        }
        hitEnemy = true;
        timerOnShieldHit.startTimer();
    }
}