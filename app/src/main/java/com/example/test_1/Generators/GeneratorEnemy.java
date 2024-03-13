package com.example.test_1.Generators;

import com.example.myframework.GraphicsGameFW;
import com.example.test_1.Objects.Enemy;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private ArrayList<Enemy> enemyArrayList;



    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer) {
        if (enemyArrayList.size() < 3) {
            addEnemy(1);
        }
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    private void addEnemy(int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            enemyArrayList.add(new Enemy(maxScreenX, maxScreenY, minScreenY, 1));
        }
    }

    public void drawing(GraphicsGameFW graphicsGameFW) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).drawing(graphicsGameFW);
        }
    }

    public void hitPlayer(Enemy enemy) {
        /**
           удаляем столкнувшийся обьект.
         */
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.remove(enemy);
        }
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

}