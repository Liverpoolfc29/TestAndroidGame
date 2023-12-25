package com.example.test_1.Classes;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.Objects.MainPlayer;

/*
    Класс управления всеми обьектами, считает удары, сблюдает колизии итд
 */
public class GameManager {

    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    MainPlayer mainPlayer;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = 0;
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
    }

    public void update() {
        mainPlayer.update();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
    }

}