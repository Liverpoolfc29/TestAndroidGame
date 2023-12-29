package com.example.myframework.Utilits;

/*
    таймер задержки
 */
public class UtilTimerDelay {
    double startTime;
    double nowTime;
    double elapsedTime;
    final double SECOND = 1000000000;

    public void startTimer() {
        /*
         джава работает с наносекундами поэтому что бы получить секунды делим на константу 1 миллирд
         */
        startTime = System.nanoTime() / SECOND;
    }

    public boolean timerDelay(double second) {
        /*
        задаем сколько секунд будет задержка
        считаем пройденное время, настоящее время минусуем когда стартовал наш таймер
         */
        nowTime = System.nanoTime() / SECOND;
        elapsedTime = nowTime - startTime;
        if (elapsedTime > second) {
            return true;
        }
        return false;
    }

}
