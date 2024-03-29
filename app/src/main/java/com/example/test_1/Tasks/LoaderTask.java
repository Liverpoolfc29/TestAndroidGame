package com.example.test_1.Tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;

import com.example.myframework.CoreGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.test_1.Interfaces.TaskCompleteListener;
import com.example.test_1.R;
import com.example.test_1.Scenes.LoaderResourceScene;
import com.example.test_1.Utillits.ResourceGame;
import com.example.test_1.Utillits.SettingsGame;

import java.util.ArrayList;

/**
 * После запуска лоадера запустится мэтот класс и его метод, и все что в методе.
 */
public class LoaderTask extends AsyncTask<Void, Integer, Void> {

    private CoreGameFW mCoreGameFW;
    private TaskCompleteListener mTaskCompleteListener;

    public LoaderTask(CoreGameFW coreGameFW, TaskCompleteListener taskCompleteListener) {
        mCoreGameFW = coreGameFW;
        mTaskCompleteListener = taskCompleteListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        /**
         Когда запускается поток, запускается этот метод который в себе запускает уепочку методов загрузки ресурсов
         (исходный класс лоадер ассерт который был раньше можно удалить его функцию будет исполнять этот)
         */
        loaderAssets();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        /**
         * Данный метод выполняется тогда когда задача полностью выполнилась
         * (как только doInBackground выполнится полностью выполняется сразу этот)
         */
        super.onPostExecute(unused);
        mTaskCompleteListener.onComplete();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        /**
         * Данный метод имеет доступ к Юай потоку и может обращатся через него в наш основной поток
         */
        super.onProgressUpdate(values);
        LoaderResourceScene.setProgressLoader(values[0]);
    }

    private void loaderAssets() {
        loadTexture(mCoreGameFW.getGraphicsFW());
        publishProgress(100);
        loadSpritePlayer(mCoreGameFW.getGraphicsFW());
        publishProgress(300);
        try {
            /**
             * остановка для демонстрации
             */
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loadSpriteEnemy(mCoreGameFW.getGraphicsFW());
        publishProgress(500);
        loadOther(mCoreGameFW.getGraphicsFW());
        publishProgress(600);
        loadAudio(mCoreGameFW);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loadSpritePlayerShieldsOn(mCoreGameFW.getGraphicsFW());
        publishProgress(700);
        loadGifts(mCoreGameFW.getGraphicsFW());
        publishProgress(800);
    }

    private void loadGifts(GraphicsGameFW graphicsGameFW) {
        ResourceGame.spriteProtector = new ArrayList<>();
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 256, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 288, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 320, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsGameFW graphicsGameFW) {
        /**
         спрайты для включенной защиты
         */
        ResourceGame.spritePlayerShieldsOn = new ArrayList<>();
        ResourceGame.spritePlayerShieldsOnBoost = new ArrayList<>();
        ResourceGame.spritePlayerShieldsOn.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 0, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 64, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 128, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 192, 128, 64, 64));

        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 0, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 64, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 128, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 192, 192, 64, 64));
    }

    private void loadAudio(CoreGameFW coreGameFW) {
        ResourceGame.gameMusic = coreGameFW.getAudioFW().newMusic("music.mp3");
        ResourceGame.hit = coreGameFW.getAudioFW().newSound("hit.ogg");
        ResourceGame.explode = coreGameFW.getAudioFW().newSound("explode.ogg");
        ResourceGame.touch = coreGameFW.getAudioFW().newSound("touch.ogg");
    }

    private void loadOther(GraphicsGameFW graphicsGameFW) {
        ResourceGame.shieldHitEnemy = graphicsGameFW.newSprite(ResourceGame.textureAtlas, 0, 128, 64, 64);
        SettingsGame.loadSettings(mCoreGameFW); // подгружаем настройки

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**
             * при зазрузке шрифта с гугла ставил выбор между способом зарузки, выше 16 API работает ниже нет.
             */
            ResourceGame.mainMenuFount = mCoreGameFW.getResources().getFont(R.font.permanent_marker);
        } else {
            ResourceGame.mainMenuFount = ResourcesCompat.getFont(mCoreGameFW.getApplicationContext(), R.font.permanent_marker);
        }
    }

    private void loadSpriteEnemy(GraphicsGameFW graphicsGameFW) {
        /**
         Загружаем картинки для противника
         */
        ResourceGame.spriteEnemy = new ArrayList<>();
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 256, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 320, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 384, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 448, 0, 64, 64));
    }

    private void loadTexture(GraphicsGameFW graphicsGameFW) {
        /**
         подгружаем файл из папки assert (атлас картинок).
         */
        ResourceGame.textureAtlas = graphicsGameFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsGameFW graphicsGameFW) {
        /**
         Нужно передавать координаты картинок из картинки(как бь вырезать их по размерам, размерами будут количество пикселей), начинаем с верхнего ула картинки координати 0.0
         и идем вправо на 64 пиксела, такого размера рисуночек первый, и так далее. Таким образом вырезаем и добавляем в масив нужные нам картиночки из общей файла картинки.
         */
        ResourceGame.spritePlayer = new ArrayList<>();
        ResourceGame.spritePlayerBoost = new ArrayList<>();
        ResourceGame.spriteExplosionPlayer = new ArrayList<>();

        ResourceGame.spritePlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 0, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 64, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 128, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 192, 0, 64, 64));

        ResourceGame.spritePlayerBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 0, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 64, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 128, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 192, 64, 64, 64));

        ResourceGame.spriteExplosionPlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 256, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 320, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 384, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 448, 256, 64, 64));
    }
}
