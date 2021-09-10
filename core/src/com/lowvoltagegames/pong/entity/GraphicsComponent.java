package com.lowvoltagegames.pong.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface GraphicsComponent {
    void render(GameObject obj, ShapeRenderer renderer);
}
