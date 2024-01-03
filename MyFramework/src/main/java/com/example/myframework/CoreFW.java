package com.example.myframework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
        класс ядро который все собирает и передает данные всем
    пояснения как масштабировать приложение под разные размеры экрана в уроке 14.
 */
public class CoreFW extends AppCompatActivity {

    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;
    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListeneerFW touchListeneerFW;
    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private float sceneWidth;
    private float sceneHeight;
    private boolean stateOnPause;
    private boolean stateOnResume;
    /*
        SharedPreferences - Класс для работы с настройками, считыванием и сохранением настроек.
     */
    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "Settings";

    /*
    Так как мы наследуемся от основоного класса AppCompatActivity нам надо создать (должен быть) класс onCreate.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        MODE_PRIVATE - создаст папку в файлаг игры доступ к которой может получить только наше приложение.
         */
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        /*
         запрещаем андроиду переодить в спящий режим пока запущено приложене
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        /*
         как только приложение запустилось нужно получить размер экрана
         */
        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        /*
         определяем фрейм буфер. Инициализируем битмапом создавая новый битмап. Наш фрейм буфер будет равен новой картинке, но
         при этом наша картинка будет иметь объявленные выше ширину и высоту экрана
        */
        frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);

        /*
         получаем ширину и высоту нашей сцены. Есть фреймбуфер определенной высоты иширины, и есть ширина и высота сцены которая будет расчитываться следующим образом :
         - берем нашу константу FRAME_BUFFER_WIDTH и делим на полученную ширину смартфона который использует приложение. И так же высоту.
        */
        sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x;
        sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y;

        loopFW = new LoopFW(this, frameBuffer);
        /*
         конструктор графики принимает АссертМенеджер (тот менеджер который мы передали с основного класса аппКомпактАктивити и фреймБуферГейм)
         */
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListeneerFW = new TouchListeneerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();
        stateOnPause = false;
        stateOnResume = false;

        setContentView(loopFW);
    }

    public CoreFW() {

    }

    public void onResume() {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    public void onPause() {
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        stateOnPause = true;

        if (isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return graphicsFW;
    }

    public TouchListeneerFW getTouchListeneerFW() {
        return touchListeneerFW;
    }

    public void setSceneFW(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Невозможно загрузить сцену");
        }
        /*
        перед тем как загрузить новую сцену текущую ставим на паузу
         */
        this.sceneFW.pause();
        /*
         уничтожаем
         */
        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.upDate();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        /*
         новая сцена
         */
        return sceneFW;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}