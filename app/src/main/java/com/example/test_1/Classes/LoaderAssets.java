package com.example.test_1.Classes;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.Utillits.ResourceGame;

import java.util.ArrayList;

/*
    Как только игра запустится, запустится лоадер и подгрузить все необходимые ресурсы заранее оперативную память.
    Класс загрузчик картинок итд. (подгружает все нужно заранее как только запустилась игра)
 */
public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpriteEnemy(graphicsFW);
        loadOther(graphicsFW);
        loadAudio(coreFW);
        loadSpritePlayerShieldsOn(graphicsFW);
        loadGifts(graphicsFW);
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        ResourceGame.spriteProtector = new ArrayList<>();
        ResourceGame.spriteProtector.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 256, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 288, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 320, 192, 32, 32));
        ResourceGame.spriteProtector.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        /*
            спрайты для включенной защиты
         */
        ResourceGame.spritePlayerShieldsOn = new ArrayList<>();
        ResourceGame.spritePlayerShieldsOnBoost = new ArrayList<>();
        ResourceGame.spritePlayerShieldsOn.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 0, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 64, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 128, 128, 64, 64));
        ResourceGame.spritePlayerShieldsOn.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 192, 128, 64, 64));

        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 0, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 64, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 128, 192, 64, 64));
        ResourceGame.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 192, 192, 64, 64));
    }

    private void loadAudio(CoreFW coreFW) {
        ResourceGame.gameMusic = coreFW.getAudioFW().newMusic("music.mp3");
        ResourceGame.hit = coreFW.getAudioFW().newSound("hit.ogg");
        ResourceGame.explode = coreFW.getAudioFW().newSound("explode.ogg");
        ResourceGame.touch = coreFW.getAudioFW().newSound("touch.ogg");
    }

    private void loadOther(GraphicsFW graphicsFW) {
        ResourceGame.shieldHitEnemy = graphicsFW.newSprite(ResourceGame.textureAtlas, 0, 128, 64, 64);
    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        /*
        Загружаем картинки для противника
         */
        ResourceGame.spriteEnemy = new ArrayList<>();
        ResourceGame.spriteEnemy.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 256, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 320, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 384, 0, 64, 64));
        ResourceGame.spriteEnemy.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 448, 0, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        /*
            подгружаем файл из папки assert (атлас картинок).
         */
        ResourceGame.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        /*
        Нужно передавать координаты картинок из картинки(как бь вырезать их по размерам, размерами будут количество пикселей), начинаем с верхнего ула картинки координати 0.0
         и идем вправо на 64 пиксела, такого размера рисуночек первый, и так далее. Таким образом вырезаем и добавляем в масив нужные нам картиночки из общей файла картинки.
         */
        ResourceGame.spritePlayer = new ArrayList<>();
        ResourceGame.spritePlayerBoost = new ArrayList<>();
        ResourceGame.spriteExplosionPlayer = new ArrayList<>();

        ResourceGame.spritePlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 0, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 64, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 128, 0, 64, 64));
        ResourceGame.spritePlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 192, 0, 64, 64));

        ResourceGame.spritePlayerBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 0, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 64, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 128, 64, 64, 64));
        ResourceGame.spritePlayerBoost.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 192, 64, 64, 64));

        ResourceGame.spriteExplosionPlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 256, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 320, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 384, 256, 64, 64));
        ResourceGame.spriteExplosionPlayer.add(graphicsFW.newSprite(ResourceGame.textureAtlas, 448, 256, 64, 64));
    }

}