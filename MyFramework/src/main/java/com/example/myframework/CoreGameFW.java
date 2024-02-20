package com.example.myframework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * класс ядро который все собирает и передает данные всем
 * пояснения как масштабировать приложение под разные размеры экрана в уроке 14.
 */
public class CoreGameFW extends AppCompatActivity {
    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;
    private final String SETTINGS = "Settings";
    private boolean isPressedKeyBack; // кнопка назад андроида
    private LoopGameFW loopGameFW;
    private GraphicsGameFW graphicsGameFW;
    private TouchListenerGameFW touchListenerGameFW;
    private AudioGameFW audioGameFW;
    private Point sizeDisplay;
    private Display display;
    private SceneGameFW sceneGameFW;
    private Bitmap frameBuffer;
    /**
     * SharedPreferences - Класс для работы с настройками, считыванием и сохранением настроек.
     */
    private SharedPreferences sharedPreferences;

    /**
     * Так как мы наследуемся от основоного класса AppCompatActivity нам надо создать (должен быть) класс onCreate.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         запрещаем андроиду переодить в спящий режим пока запущено приложене
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
        setContentView(loopGameFW);
    }

    private void init() {
        /**
         MODE_PRIVATE - создаст папку в файлаг игры доступ к которой может получить только наше приложение.
         */
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        /**
         как только приложение запустилось нужно получить размер экрана
         */
        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);
        /**
         определяем фрейм буфер. Инициализируем битмапом создавая новый битмап. Наш фрейм буфер будет равен новой картинке, но
         при этом наша картинка будет иметь объявленные выше ширину и высоту экрана
         */
        frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        /**
         *  получаем ширину и высоту нашей сцены. Есть фреймбуфер определенной высоты иширины, и есть ширина и высота сцены которая будет расчитываться следующим образом :
         *- берем нашу константу FRAME_BUFFER_WIDTH и делим на полученную ширину смартфона который использует приложение. И так же высоту.
         */
        float sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x;
        float sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y;
        audioGameFW = new AudioGameFW(this);
        loopGameFW = new LoopGameFW(this, frameBuffer);
        /**
         конструктор графики принимает АссертМенеджер (тот менеджер который мы передали с основного класса аппКомпактАктивити и фреймБуферГейм)
         */
        graphicsGameFW = new GraphicsGameFW(getAssets(), frameBuffer);
        touchListenerGameFW = new TouchListenerGameFW(loopGameFW, sceneWidth, sceneHeight);
        sceneGameFW = getStartScene();
        isPressedKeyBack = false;
    }

    public void start(SceneGameFW sceneGame) {
        sceneGameFW = sceneGame;
    }

    public CoreGameFW() {
    }

    public void onResume() {
        super.onResume();
        sceneGameFW.resume();
        loopGameFW.startGame();
    }

    public void onPause() {
        super.onPause();
        sceneGameFW.pause();
        loopGameFW.stopGame();

        if (isFinishing()) {
            sceneGameFW.dispose();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isPressedKeyBack = true;
            return true;
        }
        return false;
    }

    public GraphicsGameFW getGraphicsFW() {
        return graphicsGameFW;
    }

    public TouchListenerGameFW getTouchListenerFW() {
        return touchListenerGameFW;
    }

    public void setSceneFW(SceneGameFW sceneGameFW) {
        if (sceneGameFW == null) {
            throw new IllegalArgumentException("Невозможно загрузить сцену");
        }
        /**
         * перед тем как загрузить новую сцену текущую ставим на паузу
         */
        this.sceneGameFW.pause();
        /**
         * уничтожаем
         */
        this.sceneGameFW.dispose();
        sceneGameFW.resume();
        sceneGameFW.upDate();
        this.sceneGameFW = sceneGameFW;
    }

    public SceneGameFW getCurrentScene() {
        return sceneGameFW;
    }

    public SceneGameFW getStartScene() {
        /**
         * новая сцена
         */
        return sceneGameFW;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public AudioGameFW getAudioFW() {
        return audioGameFW;
    }

    public boolean getIsPressedKeyBack() {
        return isPressedKeyBack;
    }

    public void setIsPressedKeyBack(boolean pressedKeyBack) {
        isPressedKeyBack = pressedKeyBack;
    }


}