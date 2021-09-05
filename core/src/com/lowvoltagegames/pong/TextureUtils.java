package com.lowvoltagegames.pong;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class TextureUtils {
    public static Texture rectangle(Rectangle rectangle) {
        int height = (int) rectangle.getHeight();
        int width = (int) rectangle.getWidth();
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fillRectangle(0, 0, width, height);
        return new Texture(pixmap);
    }
}
