package com.example.test_1.Generators;

import com.example.myframework.GraphicsFW;
import com.example.test_1.Objects.Enemy;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer) {
        if (enemyArrayList.size() < 3) {
            addEnemy(speedPlayer, 3);
        }
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).Update(speedPlayer);
        }
    }

    private void addEnemy(double speedPlayer, int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            enemyArrayList.add(new Enemy(maxScreenX, maxScreenY, minScreenY, 1));
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }


}
