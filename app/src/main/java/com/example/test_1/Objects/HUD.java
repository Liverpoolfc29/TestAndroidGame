package com.example.test_1.Objects;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.GraphicsGameFW;
import com.example.test_1.R;

/**
 * Верхняя полоса экрана для отображение жизней игрока итд
 */
public class HUD {
    private final int HEIGHT_HUD = 50;
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;
    private CoreGameFW coreGameFW;

    public HUD(CoreGameFW coreGameFW) {
        this.coreGameFW = coreGameFW;
    }

    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer) {
        this.passedDistance = passedDistance;
        this.currentSpeedPlayer = currentSpeedPlayer;
        this.currentShieldsPlayer = currentShieldsPlayer;
    }

    public void drawing(GraphicsGameFW graphicsGameFW) {
        graphicsGameFW.drawLine(0, HEIGHT_HUD, graphicsGameFW.getWightFrameBuffer(), HEIGHT_HUD, Color.WHITE);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_hud_passedDistance) + ":" + passedDistance,
                10, 30, Color.GREEN, 25, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_hud_currentSpeedPlayer) + ":" + currentSpeedPlayer,
                350, 30, Color.GREEN, 25, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_hud_currentShieldsPlayer) + ":" + currentShieldsPlayer,
                650, 30, Color.GREEN, 25, null);
    }
    public int getHEIGHT_HUD() {
        return HEIGHT_HUD;
    }

}