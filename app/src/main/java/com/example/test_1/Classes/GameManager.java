package com.example.test_1.Classes;

import com.example.myframework.CollisionDetect;
import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.Generators.GeneratorBackground;
import com.example.test_1.Generators.GeneratorEnemy;
import com.example.test_1.Objects.HUD;
import com.example.test_1.Objects.MainPlayer;
import com.example.test_1.Utillits.UtilResource;

/*
    Класс управления всеми обьектами, считает удары, сблюдает колизии итд
 */
public class GameManager {

    public final static double SPEED_ANIMATION = 3;
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;
    public static boolean gameOver;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    MainPlayer mainPlayer;
    HUD hud;

    public int getPassedDistance() {
        return passedDistance;
    }

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = hud.getHEIGHT_HUD();
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight, minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;
    }

    public void update() {
        generatorBackground.updateStar(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
        /*
            пройденную дитстанция считаем от скорости движения героя (умножаем на 60 для красивого отображения не 1.2.3 а 10 20 30)
            другие данные береж похожим образом
         */
        passedDistance += mainPlayer.getSpeedPlayer();
        currentSpeedPlayer = (int) mainPlayer.getSpeedPlayer() * 60;
        currentShieldsPlayer = mainPlayer.getShieldPlayer();
        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);
        checkHit();

    }

    private void checkHit() {
        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetect.collisionDetect(mainPlayer, generatorEnemy.enemyArrayList.get(i))) {
                UtilResource.hit.play(1); // запускаем звук при столкновении
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawingStar(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }

}