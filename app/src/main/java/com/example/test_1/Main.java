package com.example.test_1;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.test_1.Classes.LoaderAssets;
import com.example.test_1.Scenes.MainManuScene;
/*
    Как только игра запустится, запустится лоадер и подгрузить все необходимые ресурсы заранее в оперативную память.
    И потом уже будет запускаться главная сцена.
 */
public class Main extends CoreFW {

    public SceneFW getStartScene() {
        LoaderAssets loaderAssets = new LoaderAssets(this, this.getGraphicsFW());
        return new MainManuScene(this);
    }

}