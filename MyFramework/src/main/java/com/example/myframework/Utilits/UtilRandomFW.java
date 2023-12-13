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

}