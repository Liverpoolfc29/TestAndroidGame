package com.example.myframework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;

import java.io.IOException;

public class AudioFW {
    AssetManager assetManager;

    public AudioFW(Activity activity) {
        /*
            C помощью этого метода можем напрямую обращаться к Активити и управлять громкостью
         */
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
    }

    public MusicFW newMusic(String filename) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = assetManager.openFd(filename);
            return new MusicFW(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить музыку" + e);
        }
    }

}