package com.lowvoltagegames.pong.entity.generic;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lowvoltagegames.pong.entity.GameObject;
import com.lowvoltagegames.pong.entity.GraphicsComponent;

public class RectangleGraphics implements GraphicsComponent {
    @Override
    public void render(GameObject obj, ShapeRenderer renderer) {
        renderer.rect(obj.x, obj.y, obj.width, obj.height);
    }
}
