package com.example.myframework;

public abstract class SceneGameFW {

    protected CoreGameFW coreGameFW;
    // ширина сцены
    protected int sceneWidth;
    //высота сцены
    protected int sceneHeight;
    // прием графики
    protected GraphicsGameFW graphicsGameFW;

    protected SceneGameFW(CoreGameFW coreGameFW) {
        this.coreGameFW = coreGameFW;
        sceneWidth = coreGameFW.getGraphicsFW().getWightFrameBuffer();
        sceneHeight = coreGameFW.getGraphicsFW().getHeightFrameBuffer();
        graphicsGameFW = coreGameFW.getGraphicsFW();
    }

    protected abstract void upDate();

    protected abstract void drawing();

    protected abstract void pause();

    protected abstract void resume();

    protected abstract void dispose();


}
