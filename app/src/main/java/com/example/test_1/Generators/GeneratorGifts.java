package com.example.test_1.Generators;

import com.example.myframework.GraphicsFW;
import com.example.myframework.Utilits.UtilTimerDelay;
import com.example.test_1.Objects.MainPlayer;
import com.example.test_1.Objects.Protector;

public class GeneratorGifts {
    Protector protector;
    UtilTimerDelay timerProtector;
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    public GeneratorGifts(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        protector = new Protector(maxScreenX, maxScreenY, minScreenY);
        timerProtector = new UtilTimerDelay();
        timerProtector.startTimer();
    }

    public void update(double speedPlayer) {
        /*
            тут надо добавить дополнительно рызных услови1 появления подарков
         */
        if (timerProtector.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            protector.update(speedPlayer);
            if (protector.getX() < minScreenX) {
                protector = null;
                protector = new Protector(maxScreenX, maxScreenY, minScreenY);
                timerProtector.startTimer();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        protector.drawing(graphicsFW);
    }

    public Protector getProtector() {
        return protector;
    }

    public void hitProtectorWithPlayer() {
        protector = null;
        protector = new Protector(maxScreenX, maxScreenY, minScreenY);
        timerProtector.startTimer();
    }

}