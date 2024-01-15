package com.example.test_1.Classes;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.Utillits.UtilResource;

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
        UtilResource.spriteProtector = new ArrayList<>();
        UtilResource.spriteProtector.add(graphicsFW.newSprite(UtilResource.textureAtlas, 256, 192, 32, 32));
        UtilResource.spriteProtector.add(graphicsFW.newSprite(UtilResource.textureAtlas, 288, 192, 32, 32));
        UtilResource.spriteProtector.add(graphicsFW.newSprite(UtilResource.textureAtlas, 320, 192, 32, 32));
        UtilResource.spriteProtector.add(graphicsFW.newSprite(UtilResource.textureAtlas, 352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        /*
            спрайты для включенной защиты
         */
        UtilResource.spritePlayerShieldsOn = new ArrayList<>();
        UtilResource.spritePlayerShieldsOnBoost = new ArrayList<>();
        UtilResource.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.textureAtlas, 0, 128, 64, 64));
        UtilResource.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.textureAtlas, 64, 128, 64, 64));
        UtilResource.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.textureAtlas, 128, 128, 64, 64));
        UtilResource.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.textureAtlas, 192, 128, 64, 64));

        UtilResource.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 0, 192, 64, 64));
        UtilResource.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 64, 192, 64, 64));
        UtilResource.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 128, 192, 64, 64));
        UtilResource.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 192, 192, 64, 64));
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResource.gameMusic = coreFW.getAudioFW().newMusic("music.mp3");
        UtilResource.hit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResource.explode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResource.touch = coreFW.getAudioFW().newSound("touch.ogg");
    }

    private void loadOther(GraphicsFW graphicsFW) {
        UtilResource.shieldHitEnemy = graphicsFW.newSprite(UtilResource.textureAtlas, 0, 128, 64, 64);
    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        /*
        Загружаем картинки для противника
         */
        UtilResource.spriteEnemy = new ArrayList<>();
        UtilResource.spriteEnemy.add(graphicsFW.newSprite(UtilResource.textureAtlas, 256, 0, 64, 64));
        UtilResource.spriteEnemy.add(graphicsFW.newSprite(UtilResource.textureAtlas, 320, 0, 64, 64));
        UtilResource.spriteEnemy.add(graphicsFW.newSprite(UtilResource.textureAtlas, 384, 0, 64, 64));
        UtilResource.spriteEnemy.add(graphicsFW.newSprite(UtilResource.textureAtlas, 448, 0, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        /*
            подгружаем файл из папки assert (атлас картинок).
         */
        UtilResource.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        /*
        Нужно передавать координаты картинок из картинки(как бь вырезать их по размерам, размерами будут количество пикселей), начинаем с верхнего ула картинки координати 0.0
         и идем вправо на 64 пиксела, такого размера рисуночек первый, и так далее. Таким образом вырезаем и добавляем в масив нужные нам картиночки из общей файла картинки.
         */
        UtilResource.spritePlayer = new ArrayList<>();
        UtilResource.spritePlayerBoost = new ArrayList<>();
        UtilResource.spriteExplosionPlayer = new ArrayList<>();

        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 0, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 64, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 128, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 192, 0, 64, 64));

        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 0, 64, 64, 64));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 64, 64, 64, 64));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 128, 64, 64, 64));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas, 192, 64, 64, 64));

        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 256, 256, 64, 64));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 320, 256, 64, 64));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 384, 256, 64, 64));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 448, 256, 64, 64));
    }

}