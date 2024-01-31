package com.example.test_1;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Classes.LoaderAssets;
import com.example.test_1.Scenes.MainManuSceneGame;
/*
    Как только игра запустится, запустится лоадер и подгрузить все необходимые ресурсы заранее в оперативную память.
    И потом уже будет запускаться главная сцена.
 */
public class Main extends CoreGameFW {

    public SceneGameFW getStartScene() {
        LoaderAssets loaderAssets = new LoaderAssets(this, this.getGraphicsFW());
        return new MainManuSceneGame(this);
    }

}