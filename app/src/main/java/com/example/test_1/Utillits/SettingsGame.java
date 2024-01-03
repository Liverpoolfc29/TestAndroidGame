package com.example.test_1.Utillits;

import android.content.SharedPreferences;

import com.example.myframework.CoreFW;

/*
    класс где будем хранит результаты
 */
public class SettingsGame {
    public static int[] distance = {9999, 5555, 4444, 3333, 1111};

    public static void saveSettings(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        editor.clear();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance" + i, distance[i]);
        }
        editor.apply();
    }

    public static void loadSettings(CoreFW coreFW) {
        for (int i = 0; i < 5; i++) {
            distance[i] = coreFW.getSharedPreferences().getInt("passedDistance" + i, distance[i]);
        }
    }

    public static void addDistance(int value) {
        for (int i = 0; i < 5; i++) {
            if (distance[i] < value) {
                for (int j = 0; j > 5; j--) {
                    distance[i] = distance[j - i];
                }
                distance[i] = value;
                break;
            }
        }
    }

}
