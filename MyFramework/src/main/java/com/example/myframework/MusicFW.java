package com.example.myframework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/*
    класс для работы с музыкой
 */
public class MusicFW implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private boolean isPrepared;

    public MusicFW(AssetFileDescriptor assetFileDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isPrepared = true;
    /*
        Вешаем слушатель на плеер
    */
        mediaPlayer.setOnCompletionListener(this);
    }

    public void play(boolean looping, float volume) {
        if (mediaPlayer.isPlaying()) {
            /*
                Если играет второй раз запускать не нужно.
             */
            return;
        }
        synchronized (this) {
            if (!isPrepared) {
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            mediaPlayer.setLooping(looping);
            mediaPlayer.setVolume(volume, volume);
            mediaPlayer.start();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        synchronized (this) {
            isPrepared = false;
        }
    }

    public void dispose() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            isPrepared = false;
        }
    }

}