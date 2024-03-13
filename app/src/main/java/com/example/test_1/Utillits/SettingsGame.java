package com.example.test_1.Utillits;

import android.content.SharedPreferences;

import com.example.myframework.CoreGameFW;

/**
    класс где будем хранит результаты
 */
public class SettingsGame {

    public static boolean musicOn = true;
    public static boolean soundOn = true;
    private static int[] distance = {9999, 5555, 4444, 3333, 1111};

    public static void saveSettings(CoreGameFW coreGameFW) {
        SharedPreferences.Editor editor = coreGameFW.getSharedPreferences().edit();
        editor.clear();
        /**
         * добавляем файлы в приватную папку нашего приложения которе будут хранить настройки
         * При запуске метода будет записано два этих значения в файл
         */
        editor.putBoolean("soundOn", soundOn);
        editor.putBoolean("musicOn", musicOn);
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance" + i, distance[i]);
        }
        editor.apply();
    }

    public static void loadSettings(CoreGameFW coreGameFW) {
        /**
         * Вытягивает сохраненные параметры из файлов нашего приложения.Указывается еще значение по умолчанию на всяк случ.
         */
        soundOn = coreGameFW.getSharedPreferences().getBoolean("soundOn", soundOn);
        musicOn = coreGameFW.getSharedPreferences().getBoolean("musicOn", musicOn);
        for (int i = 0; i < 5; i++) {
            distance[i] = coreGameFW.getSharedPreferences().getInt("passedDistance" + i, distance[i]);
        }
    }

    public static void addDistance(int value) {
        for (int i = 0; i < 5; i++) {
            if (distance[i] < value) {
                distance[i] = value;
                break;
            }
        }
    }

    public static int[] getDistance() {
        return distance;
    }
}
