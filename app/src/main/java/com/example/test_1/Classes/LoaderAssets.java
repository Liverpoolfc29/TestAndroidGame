package com.example.test_1.Classes;

import com.example.myframework.CoreGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.test_1.Utillits.ResourceGame;

import java.util.ArrayList;

/*
    Как только игра запустится, запустится лоадер и подгрузить все необходимые ресурсы заранее оперативную память.
    Класс загрузчик картинок итд. (подгружает все нужно заранее как только запустилась игра)
 */
public class LoaderAssets {

    public LoaderAssets(CoreGameFW coreGameFW, GraphicsGameFW graphicsGameFW) {
        loadTexture(graphicsGameFW);
        loadSpritePlayer(graphicsGameFW);
        loadSpriteEnemy(graphicsGameFW);
        loadOther(graphicsGameFW);
        loadAudio(coreGameFW);
        loadSpritePlayerShieldsOn(graphicsGameFW);
        loadGifts(graphicsGameFW);
    }

    private void loadGifts(GraphicsGameFW graphicsGameFW) {
        ResourceGame.spriteProtector = new ArrayList<>();
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 256, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 288, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 320, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsGameFW graphicsGameFW) {
        /*
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
    }

    private void loadSpriteEnemy(GraphicsGameFW graphicsGameFW) {
        /*
        Загружаем картинки для противника
         */
        ResourceGame.spriteEnemy = new ArrayList<>();
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 256, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 320, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 384, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsGameFW.newSprite(ResourceGame.textureAtlas, 448, 0, 64, 64));
    }

    private void loadTexture(GraphicsGameFW graphicsGameFW) {
        /*
            подгружаем файл из папки assert (атлас картинок).
         */
        ResourceGame.textureAtlas = graphicsGameFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsGameFW graphicsGameFW) {
        /*
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