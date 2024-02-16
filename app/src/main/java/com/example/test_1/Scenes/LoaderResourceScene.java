package com.example.test_1.Scenes;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Interfaces.TaskCompleteListener;
import com.example.test_1.Tasks.LoaderTask;

public class LoaderResourceScene extends SceneGameFW implements TaskCompleteListener {

    public LoaderResourceScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
        LoaderTask loaderTask = new LoaderTask(coreGameFW, this);
        loaderTask.execute();
    }

    @Override
    protected void upDate() {

    }

    @Override
    protected void drawing() {

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
        coreGameFW.setSceneFW(new MainManuSceneGame(coreGameFW));
    }
}
