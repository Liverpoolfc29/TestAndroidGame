package com.example.myframework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class AudioFW {
    private final AssetManager assetManager;
    private SoundPool soundPool;

    public AudioFW(Activity activity) {
        /*
            C помощью этого метода можем напрямую обращаться к Активити и управлять громкостью
         */
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
        versionSDK();
    }

    private void versionSDK() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*
                версии кода для разные версий андроида
             */
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public MusicFW newMusic(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(filename);
            return new MusicFW(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить музыку" + e);
        }
    }

    public SoundFW newSound(String fileName) {
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
        return new SoundFW(sound, soundPool);
    }

}