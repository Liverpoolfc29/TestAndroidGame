package com.example.test_1.Scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.test_1.Classes.GameManager;
import com.example.test_1.R;

public class GameScene extends SceneFW {

    enum GameState {
        /*
          состояния игры (игра в режиме паузы, п процессе самой игры, подготовки и окончания игры.)
         */
        READY, RUNNING, PAUSE, GAMEOVER
    }

    GameState gameState;
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        /*
        при запуске игровой сцены будет запускаться режим подготовки с вопросом к игроку готов ли он начать игру.
        */
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
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
        if (gameState == GameState.GAMEOVER) {
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
        if (gameState == GameState.GAMEOVER) {
            drawingStateGameOver();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void drawingStateGameOver() {

    }

    private void updateStateGameOver() {

    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        //graphicsFW.drawText("Сцена игры", 250, 300, Color.WHITE, 60, null);
        gameManager.drawing(coreFW, graphicsFW);
    }

    private void updateStateRunning() {
        gameManager.update();
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