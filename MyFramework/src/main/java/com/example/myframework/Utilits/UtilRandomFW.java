package com.example.myframework.Utilits;

import java.util.Random;

public class UtilRandomFW {
    /*
     для получения слуайного числа.
     возвращает случайное чисто от нуля до указаного в параметре метода.
    */
    public static int getRandomNumber(int number) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt(number);
        return randomNumber;
    }

    public static int getGap(int minNumber, int maxNumber) {
        /*
        берем два числа и между ними получаем рандомное
         */
        int gap = 0;
        gap = (int) ((Math.random() * ++maxNumber) + minNumber);
        return gap;
    }
}