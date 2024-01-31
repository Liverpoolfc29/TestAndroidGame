package com.example.myframework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class AudioGameFW {
    private final AssetManager assetManager;
    private SoundPool soundPool;

    public AudioGameFW(Activity activity) {
        /*
            C помощью этого метода можем напрямую обращаться к Активити и управлять громкостью
         */
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        //versionSDK();
    }

    private void versionSDK() {
        /*
            версии кода для разные версий андроида
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public MusicGameFW newMusic(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(filename);
            return new MusicGameFW(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить музыку" + e);
        }
    }

    public SoundGameFW newSound(String fileName) {
        /*
            Загружаем музыку
         */
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = soundPool.load(assetFileDescriptor, 0);
        return new SoundGameFW(sound, soundPool);
    }

}