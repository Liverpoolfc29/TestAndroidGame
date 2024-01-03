package com.example.myframework;

import android.view.MotionEvent;
import android.view.View;
/*
    пояснения как масштабировать приложение под разные размеры экрана в уроке 14.
 */
public class TouchListeneerFW implements View.OnTouchListener {

    float touchX;
    float touchY;

    boolean isTouchDown;
    boolean isTouchUp;

    float sceneWidth;
    float sceneHeight;

    public TouchListeneerFW(View view, float sceneWidth, float sceneHeight) {
        /*
        подключаем слушатель события у нашему view.
         */
        view.setOnTouchListener(this);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        /*
        в этом методе есть масштабирование под размер любого экрана с помощью умножения на коэфициент. детально об этой сложной теме в уроке 14.
         принимает компонент view и событие, нам это событие надо обработать, нужно понять что за событие, отжал пользователь палец или наоборот нажал.
         И при этом надо понимать в каком месте
        */
        synchronized (this) {
            isTouchDown = false;
            isTouchUp = false;
            /*
             event.getAction() - возвращает именно событие по нажатию. И проверяем, если это событие было нажатие пользователем тогда записываем в каких координатах
             пользователь нажал, и повторяем процесс на событие отжатия пальца.
            */
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchX = event.getX() * sceneWidth;
                    touchY = event.getY() * sceneHeight;
                    isTouchDown = true;
                    isTouchUp = false;
                    break;
                case MotionEvent.ACTION_UP:
                    touchX = event.getX() * sceneWidth;
                    touchY = event.getY() * sceneHeight;
                    isTouchDown = false;
                    isTouchUp = true;
                    break;
            }
        }
        return true;
    }

    public boolean getTouchUp(int x, int y, int touchWidth, int touchHeight) {
        /*
         данный метод проверяет было ли нажатие в данной области. Принимает координату по иксу игрику и величину нашей области.
         */
        if (isTouchUp) {
            if (touchX >= x && touchX <= x + touchWidth - 1 && touchY <= y && touchY >= y - (touchHeight - 1)) {
                /*
                 если данное условие соблюдено, это значит что пользователь нажал именно на ту область которую мы переали как параметр в этот метод.
                 */
                isTouchUp = false;
                return true;
            }
        }
        return false;
    }

    public boolean getTouchDown(int x, int y, int touchWidth, int touchHeight) {
        /*
         такой же метод для нажатия
         */
        if (isTouchDown) {
            if (touchX >= x && touchX <= x + touchWidth - 1 && touchY <= y && touchY >= y - (touchHeight - 1)) {
                isTouchDown = false;
                return true;
            }
        }
        return false;
    }

}