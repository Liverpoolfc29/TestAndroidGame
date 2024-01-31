package com.example.myframework;

public abstract class SceneGameFW {

    protected CoreGameFW coreGameFW;
    // ширина сцены
    protected int sceneWidth;
    //высота сцены
    protected int sceneHeight;
    // прием графики
    protected GraphicsGameFW graphicsGameFW;

    public SceneGameFW(CoreGameFW coreGameFW) {
        this.coreGameFW = coreGameFW;
        sceneWidth = coreGameFW.getGraphicsFW().getWightFrameBuffer();
        sceneHeight = coreGameFW.getGraphicsFW().getHeightFrameBuffer();
        graphicsGameFW = coreGameFW.getGraphicsFW();
    }

    public abstract void upDate();

    public abstract void drawing();

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();


}
