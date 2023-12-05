package com.example.myframework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// 31 minute
public class CoreFW extends AppCompatActivity {

    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private float sceneWidth;
    private float sceneHeight;

    // Так как мы наследуемся от основоного класса AppCompatActivity нам надо создать (должен быть) класс onCreate.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // запрещаем андроиду переодить в спящий режим пока запущено приложене
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // как только приложение запустилось нужно получить размер экрана
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

    }

    public CoreFW() {

    }

}
