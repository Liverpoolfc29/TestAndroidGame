package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreGameFW;
import com.example.myframework.SceneGameFW;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.R;
import com.example.test_1.Utillits.SettingsGame;
import com.example.test_1.Utillits.ResourceGame;

public class GameScene extends SceneGameFW {
    enum GameState {
        /**
         * состояния игры (игра в режиме паузы, п процессе самой игры, подготовки и окончания игры.)
         */
        READY, RUNNING, PAUSE, GAME_STATE
    }

    private GameState gameState;
    private GameManager gameManager;

    public GameScene(CoreGameFW coreGameFW) {
        super(coreGameFW);
        /**
         при запуске игровой сцены будет запускаться режим подготовки с вопросом к игроку готов ли он начать игру.
         */
        init(coreGameFW);
        if (SettingsGame.musicOn) {
            ResourceGame.gameMusic.play(true, 0.5f);
        }
    }

    private void init(CoreGameFW coreGameFW) {
        gameState = GameState.READY;
        gameManager = new GameManager(coreGameFW, sceneWidth, sceneHeight);
    }

    @Override
    public void upDate() {
        /**
         Исходя из того в каком состоянии находится игра будет запускаться нужный метод.
         Метод апдейт выполняется 60 раз в сек
         */
        if (gameState == GameState.READY) {
            updateStateReady();
        }
        if (gameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (gameState == GameState.GAME_STATE) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        /**
         Метод рисования выполняется 60 раз в сек.
         Исходя из того в каком состоянии находится игра будет запускаться нужный метод для отрисовки.
         */
        graphicsGameFW.clearScene(Color.BLACK);

        if (gameState == GameState.READY) {
            drawingStateReady();
        }
        if (gameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (gameState == GameState.GAME_STATE) {
            drawingStateGameOver();
        }
    }

    @Override
    public void pause() {
        ResourceGame.gameMusic.stop();
    }

    @Override
    public void resume() {
        if (SettingsGame.musicOn) {
            ResourceGame.gameMusic.play(true, 0.5f);
        }
    }

    @Override
    public void dispose() {
        ResourceGame.explode.dispose();
        ResourceGame.hit.dispose();
        ResourceGame.touch.dispose();
        ResourceGame.gameMusic.dispose();
    }

    private void drawingStateGameOver() {
        graphicsGameFW.clearScene(Color.BLACK);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_gameScene_stateGameOver_gameOver), 250, 300, Color.WHITE, 60, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_gameScene_stateGameOver_restart), 250, 360, Color.WHITE, 30, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_gameScene_stateGameOver_exit), 250, 420, Color.WHITE, 30, null);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_gameScene_stateGameOver_distance) + " : " + gameManager.getPassedDistance(), 250, 200, Color.WHITE, 30, null);
    }

    private void updateStateGameOver() {
        /**
         Когда игра в сцене конца игры ставим слушатель на нажатие на экран, и дальше по нажатию смотрим что выбрал пользователь
         */
        SettingsGame.addDistance(gameManager.getPassedDistance());
        if (coreGameFW.getTouchListenerFW().getTouchUp(250, 360, 200, 35)) {
            coreGameFW.setSceneFW(new GameScene(coreGameFW));
        }
        if (coreGameFW.getTouchListenerFW().getTouchUp(250, 420, 200, 35)) {
            coreGameFW.setSceneFW(new MainManuScene(coreGameFW));
        }
    }

    private void drawingStatePause() {
        coreGameFW.getGraphicsFW().drawText("Pause", 200, 300, Color.YELLOW, 50, null);
    }

    private void updateStatePause() {
        if (coreGameFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            gameState = GameState.RUNNING;
        }
    }

    private void drawingStateRunning() {
        graphicsGameFW.clearScene(Color.BLACK);
        gameManager.drawing(graphicsGameFW);
    }

    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver) {
            gameState = GameState.GAME_STATE;
        }
        if (coreGameFW.getIsPressedKeyBack()) {
            gameState = GameState.PAUSE;
            coreGameFW.setIsPressedKeyBack(false);
        }
    }

    private void drawingStateReady() {
        graphicsGameFW.clearScene(Color.GREEN);
        graphicsGameFW.drawText(coreGameFW.getString(R.string.txt_gameScene_stateReady_ready), 250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
        /**
         * проверяем нажал ли игрок подтверждение в сцене подготовки, подтверждением считается любое нажатия (отжатие пальца от экрана) на экран в любом месте, для
         *  этого и указываем в границах слушателя нажатий весь экран а не конкретную область.
         *  и если подтверждение было переводим игру в следущее состояние.
         */
        if (coreGameFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            gameState = GameState.RUNNING;
        }
    }

}