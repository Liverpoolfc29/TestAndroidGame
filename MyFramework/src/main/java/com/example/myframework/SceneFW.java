package com.example.myframework;

public abstract class SceneFW {

    protected CoreFW coreFW;
    // ширина сцены
    protected int sceneWidth;
    //высота сцены
    protected int sceneHeight;
    // прием графики
    protected GraphicsFW graphicsFW;

    public SceneFW(CoreFW coreFW) {
        this.coreFW = coreFW;
        sceneWidth = coreFW.getGraphicsFW().getWightFrameBuffer();
        sceneHeight = coreFW.getGraphicsFW().getHeightFrameBuffer();
        graphicsFW = coreFW.getGraphicsFW();
    }

    public abstract void upDate();

    public abstract void drawing();

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();


}
