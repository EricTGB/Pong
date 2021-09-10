package com.lowvoltagegames.pong.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lowvoltagegames.pong.GameScreen;

public abstract class GameObject {
    public float x, y;
    public float width, height;
    public float velocity;

    private final InputComponent input;
    private final PhysicsComponent physics;
    private final GraphicsComponent graphics;

    public GameObject(InputComponent input, PhysicsComponent physics, GraphicsComponent graphics) {
        this.input = input;
        this.physics = physics;
        this.graphics = graphics;
    }

    public void update(GameScreen screen, float delta) {
        input.update(this);
        physics.update(this, screen, delta);
    }

    public void render(ShapeRenderer renderer) {
        graphics.render(this, renderer);
    }

    public void collision(GameObject obj) {
        physics.collision(this, obj);
    }
}
