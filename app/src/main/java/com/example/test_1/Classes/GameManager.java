package com.example.test_1.Classes;

import com.example.myframework.Utilits.CollisionDetect;
import com.example.myframework.CoreGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.test_1.Generators.GeneratorBackground;
import com.example.test_1.Generators.GeneratorEnemy;
import com.example.test_1.Generators.GeneratorGifts;
import com.example.test_1.Objects.HUD;
import com.example.test_1.Objects.MainPlayer;
import com.example.test_1.Utillits.ResourceGame;

/**
    Класс управления всеми обьектами, считает удары, сблюдает колизии итд
 */
public class GameManager {

    public final static double SPEED_ANIMATION = 3;
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;
    public static boolean gameOver;
    private GeneratorBackground generatorBackground;
    private GeneratorEnemy generatorEnemy;
    private GeneratorGifts generatorGifts;
    private MainPlayer mainPlayer;
    private HUD hud;

    public GameManager(CoreGameFW coreGameFW, int sceneWidth, int sceneHeight) {
        init(coreGameFW, sceneWidth,sceneHeight);
    }

    private void init(CoreGameFW coreGameFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreGameFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = hud.getHEIGHT_HUD();
        mainPlayer = new MainPlayer(coreGameFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight, minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, minScreenY);
        generatorGifts = new GeneratorGifts(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;
    }

    public void update() {
        updateObjects();
        /**
            пройденную дитстанция считаем от скорости движения героя (умножаем на 60 для красивого отображения не 1.2.3 а 10 20 30)
            другие данные береж похожим образом
         */
        passedDistance += mainPlayer.getSpeedPlayer();
        currentSpeedPlayer = (int) mainPlayer.getSpeedPlayer() * 60;
        currentShieldsPlayer = mainPlayer.getShieldPlayer();
        checkHit();
    }

    private void updateObjects() {
        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);
        generatorBackground.updateStar(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
        generatorGifts.update(mainPlayer.getSpeedPlayer());
    }

    private void checkHit() {
        for (int i = 0; i < generatorEnemy.getEnemyArrayList().size(); i++) {
            if (CollisionDetect.collisionDetect(mainPlayer, generatorEnemy.getEnemyArrayList().get(i))) {
                ResourceGame.hit.play(1); // запускаем звук при столкновении
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.getEnemyArrayList().get(i));
            }
        }
        if (CollisionDetect.collisionDetect(mainPlayer, generatorGifts.getProtector())) {
            hitPlayerWithProtector();
        }
    }

    private void hitPlayerWithProtector() {
        mainPlayer.hitProtector();
        generatorGifts.hitProtectorWithPlayer();
    }

    public void drawing(GraphicsGameFW graphicsGameFW) {
        mainPlayer.drawing(graphicsGameFW);
        generatorBackground.drawingStar(graphicsGameFW);
        generatorEnemy.drawing(graphicsGameFW);
        hud.drawing(graphicsGameFW);
        generatorGifts.drawing(graphicsGameFW);
    }

    public int getPassedDistance() {
        return passedDistance;
    }

}