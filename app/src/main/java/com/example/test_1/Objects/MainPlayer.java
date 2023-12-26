package com.example.test_1.Objects;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
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

    boolean boosting;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        /*
            обозначим место появления и начальную скорость.
         */
        x = 20;
        y = 200;
        speed = 3;
        boosting = false;
        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        /*
            у картинки левый верхний угол точка отсчета поэтому нужно подрезать ораничение на размер картинкии (-64)
         */
        this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
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
        if (boosting) {
            animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
        } else {
            animMainPlayer.drawingAnimation(graphicsFW, x, y);
        }
    }

    public double getSpeedPlayer() {
        return speed;
    }

}