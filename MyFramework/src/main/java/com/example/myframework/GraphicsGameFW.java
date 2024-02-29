package com.example.myframework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

/**
 * пояснения как масштабировать приложение под разные размеры экрана в уроке 14.
 */
public class GraphicsGameFW {
    /**
     * для работы с файлами
     */
    private AssetManager assetManagerGame;
    /**
     * для работы сграфикой, будет подстраивать размеры графики под различные велиыины экранов.
     */
    private Bitmap frameBufferGame;
    /**
     * для приема графики
     */
    private Canvas canvasGame;
    /**
     * для работы с графикой
     */
    private Paint paintGame;

    public GraphicsGameFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        init(assetManagerGame, frameBufferGame);
    }

    private void init(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame = assetManagerGame;
        this.frameBufferGame = frameBufferGame;
        /**
         * передаем туда фреймБуфер, не рисуем на самом холсте канвас.
         */
        this.canvasGame = new Canvas(frameBufferGame);
        this.paintGame = new Paint();
    }

    /**
     * для очистки т.е. закраски фреймбуфера данным цветом.
     */
    public void clearScene(int colorRGB) {
        canvasGame.drawRGB(colorRGB, colorRGB, colorRGB); // принимает три параметра R G B но мы передаем одинаковые значения что бы получить один цвет
    }

    public void drawPixel(int x, int y, int color) {
        paintGame.setColor(color);
        canvasGame.drawPoint(x, y, paintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        paintGame.setColor(color);
        canvasGame.drawLine(startX, startY, stopX, stopY, paintGame);
    }

    public void drawText(String text, int x, int y, int color, int sizeText, Typeface font) {
        paintGame.setColor(color);
        paintGame.setTextSize(sizeText);
        paintGame.setTypeface(font);
        canvasGame.drawText(text, x, y, paintGame);
    }

    public void drawTexture(Bitmap textureGame, int x, int y) {
        canvasGame.drawBitmap(textureGame, x, y, null);
    }

    /**
     * получение ширины фреймбуфера
     */
    public int getWightFrameBuffer() {
        return frameBufferGame.getWidth();
    }

    /**
     * высоты фреймбуфера
     */
    public int getHeightFrameBuffer() {
        return frameBufferGame.getHeight();
    }

    public Bitmap newTexture(String fileName) {
        InputStream inputStream = null;
        /**
         * для текстур
         */
        Bitmap textureGame;
        try {
            inputStream = assetManagerGame.open(fileName);
            /**
             * в текстуру передаем поток с файла
             */
            textureGame = BitmapFactory.decodeStream(inputStream);
            if (textureGame == null) {
                throw new RuntimeException("Невозможно загрузить файл " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("проблема с загрузкой файла" + e);
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return textureGame;
    }

    public Bitmap newSprite(Bitmap textureAtlas, int x, int y, int widthSprite, int heightSprite) {
        /**
         * принимаем атлас текстур, координаты, и по этим координатам мы получаем определенную картинку и возвращаем ее.
         */
        Bitmap newSprite = Bitmap.createBitmap(textureAtlas, x, y, widthSprite, heightSprite);
        return newSprite;
    }

}