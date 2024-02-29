package com.example.test_1.Objects;

import android.graphics.Rect;

import com.example.myframework.CoreGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.myframework.ObjectGameFW;
import com.example.myframework.Utilits.UtilTimerDelay;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.Utillits.ResourceGame;
import com.example.myframework.AnimationGameFW;

/**
 * Каждый обьект и наш игрок должен знать габариты экрана (максимальную высоту и ширину) что бы не вылетать за его пределы.
 */
public class MainPlayer extends ObjectGameFW {
    private final int GRAVITY = -4;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;
    private CoreGameFW coreGameFW;
    /**
     * для анимации игрока в обычном состоянии
     */
    private AnimationGameFW animMainPlayer;
    /**
     * для анимации игрока в режиме ускорения
     */
    private AnimationGameFW animMainPlayerBoost;
    private AnimationGameFW animExplosionPlayer;
    private AnimationGameFW animPlayerShieldsOn;
    private AnimationGameFW animPlayerShieldsOnBoost;
    private UtilTimerDelay timerOnShieldHit;
    private UtilTimerDelay timerOnGameOver;
    private UtilTimerDelay timerShieldsOn;
    private boolean boosting;
    private int shieldsPlayer;
    private boolean hitEnemy;
    private boolean isGameOver;
    private static boolean shieldsOn;

    public MainPlayer(CoreGameFW coreGameFW, int maxScreenX, int maxScreenY, int minScreenY) {
        init(coreGameFW, maxScreenX, maxScreenY, minScreenY);
        initAnimation();
    }

    private void init(CoreGameFW coreGameFW, int maxScreenX, int maxScreenY, int minScreenY) {
        shieldsOn = false;
        /**
         * обозначим место появления и начальную скорость.
         */
        x = 20;
        y = 200;
        speed = GameManager.SPEED_ANIMATION;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;
        /**
         * вычисляем радиус фигурки, размер фигурки 64 на 64 пикселя, радиус нужен от ее центра координат в середине фигурки, значит берем половину ее
         * размера 64\2 и получаем радиус от центра 32
        */
        radius = (double) ResourceGame.spritePlayer.get(0).getHeight() / 4;
        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();
        timerShieldsOn = new UtilTimerDelay();
        this.coreGameFW = coreGameFW;
        this.maxScreenX = maxScreenX;
        /**
         * у картинки левый верхний угол точка отсчета поэтому нужно подрезать ораничение на размер картинкии (-64)
         */
        this.maxScreenY = maxScreenY - ResourceGame.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
    }

    private void initAnimation() {
        /**
         * загрузили 4 спрайта из нашего масива  картинок с анимацией игрока
         */
        animMainPlayer = new AnimationGameFW(speed, ResourceGame.spritePlayer.get(0),
                ResourceGame.spritePlayer.get(1),
                ResourceGame.spritePlayer.get(2),
                ResourceGame.spritePlayer.get(3));
        /**
         * создаем новую анимацию для игрока в режиме ускорения (подгружаем другие картинки для этого)
        */
        animMainPlayerBoost = new AnimationGameFW(speed, ResourceGame.spritePlayerBoost.get(0),
                ResourceGame.spritePlayerBoost.get(1),
                ResourceGame.spritePlayerBoost.get(2),
                ResourceGame.spritePlayerBoost.get(3));

        animExplosionPlayer = new AnimationGameFW(speed, ResourceGame.spriteExplosionPlayer.get(0),
                ResourceGame.spriteExplosionPlayer.get(1),
                ResourceGame.spriteExplosionPlayer.get(2),
                ResourceGame.spriteExplosionPlayer.get(3));

        animPlayerShieldsOn = new AnimationGameFW(speed, ResourceGame.spritePlayerShieldsOn.get(0),
                ResourceGame.spritePlayerShieldsOn.get(1),
                ResourceGame.spritePlayerShieldsOn.get(2),
                ResourceGame.spritePlayerShieldsOn.get(3));

        animPlayerShieldsOnBoost = new AnimationGameFW(speed, ResourceGame.spritePlayerShieldsOnBoost.get(0),
                ResourceGame.spritePlayerShieldsOnBoost.get(1),
                ResourceGame.spritePlayerShieldsOnBoost.get(2),
                ResourceGame.spritePlayerShieldsOnBoost.get(3));
    }

    public void update() {
        /**
         * Проверяем на нажатие. Если нажал на любую область экрана включаем буст, если отпустил выключаем. (в параметры метода передаем весь размер экрана)
         */
        if (coreGameFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (coreGameFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }
        if (timerShieldsOn.timerDelay(5)) {
            shieldsOn = false;
        }
        updateBoosting();
        hitBox = new Rect(x, y,
                ResourceGame.spritePlayer.get(0).getWidth(),
                ResourceGame.spritePlayer.get(0).getHeight());
        if (isGameOver) {
            animExplosionPlayer.runAnimation();
        }
    }

    private void updateBoosting() {
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
        /**
         * проверка вылетов за экран
         */
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
        }
        /**
         * Делаем переключатели между видами анимациями с броней без итд
        */
        if (boosting) {
            if (shieldsOn) {
                animPlayerShieldsOnBoost.runAnimation();
            } else {
                animMainPlayerBoost.runAnimation();
            }
        } else if (shieldsOn) {
            animPlayerShieldsOn.runAnimation();
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

    public void drawing(GraphicsGameFW graphicsGameFW) {
        /**
         * делаем переключение анимации и обычного игрока на игрока в ускорее с помощью бул переменной
         */
        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    if (shieldsOn) {
                        animPlayerShieldsOnBoost.drawingAnimation(graphicsGameFW, x, y);
                    } else {
                        animMainPlayerBoost.drawingAnimation(graphicsGameFW, x, y);
                    }
                } else if (shieldsOn) {
                    animPlayerShieldsOn.drawingAnimation(graphicsGameFW, x, y);
                } else {
                    animMainPlayer.drawingAnimation(graphicsGameFW, x, y);
                }
            } else {
                graphicsGameFW.drawTexture(ResourceGame.shieldHitEnemy, x, y);
                if (timerOnShieldHit.timerDelay(0.2)) {
                    hitEnemy = false;
                } else {
                    hitEnemy = true;
                }
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsGameFW, x, y);
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
        if (!shieldsOn) {
            shieldsPlayer--;
            if (shieldsPlayer < 0) {
                ResourceGame.explode.play(1); // когда закончились жизни запускаем музыку убийства
                isGameOver = true;
                timerOnGameOver.startTimer();
            }
            hitEnemy = true;
            timerOnShieldHit.startTimer();
        }
    }

    public static boolean isShieldsOn() {
        return shieldsOn;
    }

    public void hitProtector() {
        shieldsOn = true;
        timerShieldsOn.startTimer();
    }

}