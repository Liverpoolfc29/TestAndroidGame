package com.example.test_1.Objects;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.test_1.R;

/*
    Верхняя полоса экрана для отображение жизней игрока итд
 */
public class HUD {
    private final int HEIGHT_HUD = 50;
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;
    CoreFW coreFW;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer) {
        this.passedDistance = passedDistance;
        this.currentSpeedPlayer = currentSpeedPlayer;
        this.currentShieldsPlayer = currentShieldsPlayer;
    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0, HEIGHT_HUD, graphicsFW.getWightFrameBuffer(), HEIGHT_HUD, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_passedDistance) + ":" + passedDistance,
                10, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentSpeedPlayer) + ":" + currentSpeedPlayer,
                350, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentShieldsPlayer) + ":" + currentShieldsPlayer,
                650, 30, Color.GREEN, 25, null);
    }
    public int getHEIGHT_HUD() {
        return HEIGHT_HUD;
    }

}