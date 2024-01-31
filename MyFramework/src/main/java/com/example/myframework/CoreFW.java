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
    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private AudioFW audioFW;
    private SceneFW sceneFW;
    /*
        SharedPreferences - Класс для работы с настройками, считыванием и сохранением настроек.
     */
    private SharedPreferences sharedPreferences;

    /*
    Так как мы наследуемся от основоного класса AppCompatActivity нам надо создать (должен быть) класс onCreate.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        MODE_PRIVATE - создаст папку в файлаг игры доступ к которой может получить только наше приложение.
         */
        String SETTINGS = "Settings";
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        /*
         запрещаем андроиду переодить в спящий режим пока запущено приложене
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        /*
         как только приложение запустилось нужно получить размер экрана
         */
        Point sizeDisplay = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);
        /*
         определяем фрейм буфер. Инициализируем битмапом создавая новый битмап. Наш фрейм буфер будет равен новой картинке, но
         при этом наша картинка будет иметь объявленные выше ширину и высоту экрана
        */
        float FRAME_BUFFER_WIDTH = 800;
        float FRAME_BUFFER_HEIGHT = 600;
        Bitmap frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        /*
         получаем ширину и высоту нашей сцены. Есть фреймбуфер определенной высоты иширины, и есть ширина и высота сцены которая будет расчитываться следующим образом :
         - берем нашу константу FRAME_BUFFER_WIDTH и делим на полученную ширину смартфона который использует приложение. И так же высоту.
        */
        float sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x;
        float sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y;

        audioFW = new AudioFW(this);
        loopFW = new LoopFW(this, frameBuffer);
        /*
         конструктор графики принимает АссертМенеджер (тот менеджер который мы передали с основного класса аппКомпактАктивити и фреймБуферГейм)
         */
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();
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

        if (isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return graphicsFW;
    }

    public TouchListenerFW getTouchListeneerFW() {
        return touchListenerFW;
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

    public AudioFW getAudioFW() {
        return audioFW;
    }

}