package com.example.test_1.Classes;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.Generators.GeneratorBackground;
import com.example.test_1.Generators.GeneratorEnemy;
import com.example.test_1.Objects.MainPlayer;

/*
    Класс управления всеми обьектами, считает удары, сблюдает колизии итд
 */
public class GameManager {

    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    MainPlayer mainPlayer;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = 0;
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, minScreenY);
    }

    public void update() {
        generatorBackground.updateStar(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawingStar(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
    }

}