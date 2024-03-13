package com.example.myframework;

import android.graphics.Bitmap;

/**
 *** Фреймворк никак не зависит с игрой он просто принимает какие то параметры и выдает
 ** Класс принимает в конструктор скорость и 4 картинки и выдает анимацию этого.
 * Изначально вся анимация будет начинаться с первого спрайта.
 * frames = 4; - кол кадров.
 */
public class AnimationGameFW {
    private double speedAnimation;
    private int delayIndex;
    private int countFrames;
    private int frames;
    private Bitmap sprite;
    private Bitmap sprite1;
    private Bitmap sprite2;
    private Bitmap sprite3;
    private Bitmap sprite4;

    public AnimationGameFW(double speedAnimation, Bitmap sprite1, Bitmap sprite2, Bitmap sprite3, Bitmap sprite4) {
        init(speedAnimation, sprite1, sprite2, sprite3, sprite4);
    }

    private void init(double speedAnimation, Bitmap sprite1, Bitmap sprite2, Bitmap sprite3, Bitmap sprite4) {
        sprite = sprite1;
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        this.sprite3 = sprite3;
        this.sprite4 = sprite4;
        this.speedAnimation = speedAnimation;
        frames = 4;
    }

    public void runAnimation() {
        delayIndex++;
        if (delayIndex > speedAnimation) {
            delayIndex = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        if (countFrames == 0) {
            sprite = sprite1;
        }
        if (countFrames == 1) {
            sprite = sprite2;
        }
        if (countFrames == 2) {
            sprite = sprite3;
        }
        if (countFrames == 3) {
            sprite = sprite4;
        }
        countFrames++;
        if (countFrames > frames) {
            countFrames = 0;
        }
    }

    public void drawingAnimation(GraphicsGameFW graphicsGameFW, int x, int y) {
        graphicsGameFW.drawTexture(sprite, x, y);
    }

}