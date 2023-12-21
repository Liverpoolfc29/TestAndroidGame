package com.example.test_1.Classes;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.Utilits.UtilResource;

import java.util.ArrayList;

/*
    Как только игра запустится, запустится лоадер и подгрузить все необходимые ресурсы заранее оперативную память.
    Класс загрузчик картинок итд. (подгружает все нужно заранее как только запустилась игра)
 */
public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);


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
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 0, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 64, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 128, 0, 64, 64));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas, 192, 0, 64, 64));
    }

}