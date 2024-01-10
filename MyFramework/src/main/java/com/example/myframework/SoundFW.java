package com.example.myframework;

import android.media.SoundPool;
/*
    Класс для работы со звуками
 */
public class SoundFW {
    int sound;
    SoundPool soundPool;

    public SoundFW(int sound, SoundPool soundPool) {
        this.sound = sound;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        /*
            метод запускающий звук, принимает левый громкость правый громкость, повторение итд.
         */
        soundPool.play(sound, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        /*
            удаление
         */
        soundPool.unload(sound);
    }

}