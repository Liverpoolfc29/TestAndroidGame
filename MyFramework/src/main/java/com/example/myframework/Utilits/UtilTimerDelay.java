package com.example.myframework.Utilits;

/**
    таймер задержки
 */
public class UtilTimerDelay {
    private double startTime;
    private double nowTime;
    private double elapsedTime;
    private final double SECOND = 1000000000;

    public void startTimer() {
        /**
            джава работает с наносекундами поэтому что бы получить секунды делим на константу 1 миллирд
         */
        startTime = System.nanoTime() / SECOND;
    }

    public boolean timerDelay(double second) {
        /**
         * задаем сколько секунд будет задержка
         * считаем пройденное время, настоящее время минусуем когда стартовал наш таймер
         */
        nowTime = System.nanoTime() / SECOND;
        elapsedTime = nowTime - startTime;
        return elapsedTime > second;
    }

}
