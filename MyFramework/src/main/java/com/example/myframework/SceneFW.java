package com.example.myframework;

public abstract class SceneFW {

    private CoreFW coreFW;
    // ширина сцены
    public int sceneWight;
    //высота сцены
    public int sceneHeight;
    // прием графики
    private GraphicsFW graphicsFW;

    public SceneFW(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public abstract void upDate();

    public abstract void drawing();

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();


}
