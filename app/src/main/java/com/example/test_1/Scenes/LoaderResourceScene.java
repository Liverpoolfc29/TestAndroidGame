package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Interfaces.TaskCompleteListener;
import com.example.test_1.R;
import com.example.test_1.Tasks.LoaderTask;

public class LoaderResourceScene extends SceneGameFW implements TaskCompleteListener {

    private static int mProgressLoader;

    public LoaderResourceScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
        mProgressLoader = 0;
        LoaderTask loaderTask = new LoaderTask(coreGameFW, this);
        loaderTask.execute();
    }

    @Override
    protected void upDate() {

    }

    @Override
    protected void drawing() {
        coreGameFW.getGraphicsFW().clearScene(Color.BLACK);
        coreGameFW.getGraphicsFW().drawText(coreGameFW.getString(R.string.loading), 100, 100, Color.GREEN, 100, null);
        coreGameFW.getGraphicsFW().drawLine(0, 500, mProgressLoader, 500, Color.YELLOW);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void dispose() {

    }

    @Override
    public void onComplete() {
        coreGameFW.setSceneFW(new MainManuScene(coreGameFW));
    }

    public static int getProgressLoader() {
        return mProgressLoader;
    }

    public static void setProgressLoader(int progressLoader) {
        LoaderResourceScene.mProgressLoader = progressLoader;
    }

}