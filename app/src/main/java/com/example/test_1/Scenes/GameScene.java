package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.R;
import com.example.test_1.Utillits.SettingsGame;
import com.example.test_1.Utillits.ResourceGame;

public class GameScene extends SceneFW {
    enum GameState {
        /*
            состояния игры (игра в режиме паузы, п процессе самой игры, подготовки и окончания игры.)
         */
        READY, RUNNING, PAUSE, GAME_STATE
    }

    private GameState gameState;
    private GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        /*
            при запуске игровой сцены будет запускаться режим подготовки с вопросом к игроку готов ли он начать игру.
        */
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
        ResourceGame.gameMusic.play(true, 0.5f);
    }

    @Override
    public void upDate() {
        /*
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
        /*
         Метод рисования выполняется 60 раз в сек.
         Исходя из того в каком состоянии находится игра будет запускаться нужный метод для отрисовки.
        */
        graphicsFW.clearScene(Color.BLACK);

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
        ResourceGame.gameMusic.play(true, 0.5f);
    }

    @Override
    public void dispose() {
        ResourceGame.explode.dispose();
        ResourceGame.hit.dispose();
        ResourceGame.touch.dispose();
        ResourceGame.gameMusic.dispose();
    }

    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver), 250, 300, Color.WHITE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart), 250, 360, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit), 250, 420, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance) + " : " + gameManager.getPassedDistance(), 250, 200, Color.WHITE, 30, null);
    }

    private void updateStateGameOver() {
    /*
        Когда игра в сцене конца игры ставим слушатель на нажатие на экран, и дальше по нажатию смотрим что выбрал пользователь
    */
        SettingsGame.addDistance(gameManager.getPassedDistance());
        if (coreFW.getTouchListeneerFW().getTouchUp(250, 360, 200, 35)) {
            coreFW.setSceneFW(new GameScene(coreFW));
        }
        if (coreFW.getTouchListeneerFW().getTouchUp(250, 420, 200, 35)) {
            coreFW.setSceneFW(new MainManuScene(coreFW));
        }
    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        gameManager.drawing(graphicsFW);
    }

    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver) {
            gameState = GameState.GAME_STATE;
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready), 250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
        /*
         проверяем нажал ли игрок подтверждение в сцене подготовки, подтверждением считается любое нажатия (отжатие пальца от экрана) на экран в любом месте, для
         этого и указываем в границах слушателя нажатий весь экран а не конкретную область.
         и если подтверждение было переводим игру в следущее состояние.
        */
        if (coreFW.getTouchListeneerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            gameState = GameState.RUNNING;
        }
    }

}