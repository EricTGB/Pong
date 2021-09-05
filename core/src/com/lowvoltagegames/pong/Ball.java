package com.lowvoltagegames.pong;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Rectangle {
    public final float WIDTH = 8;
    public final float HEIGHT = 8;
    private final float MIN_ANGLE = (float) (4 * Math.PI / 6);
    private final float MAX_ANGLE = (float) (8 * Math.PI / 6);

    private float angle;
    private float ballSpeed;

    private final Vector2 vel;
    private final Vector2 pos;
    private final Vector2 dis;

    public Ball() {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.pos = new Vector2();
        this.dis = new Vector2();
        this.vel = new Vector2();

        initAngle();
    }

    public void initAngle() {
        this.angle = MathUtils.random(MIN_ANGLE, MAX_ANGLE);

        vel.x = MathUtils.cos(angle) * MathUtils.randomSign();
        vel.y = MathUtils.sin(angle);

        ballSpeed = 200;
    }

    public void updatePos(float delta) {
        dis.setZero().add(vel).scl(ballSpeed * delta);
        setPosition(getPosition(pos).add(dis));
    }

    public void onHit(Paddle paddle) {
        float deltaY = (y + height / 2) - (paddle.y + paddle.height / 2);
        deltaY = deltaY / paddle.height + 0.5f; //normalise delta between 0 and 1
        angle = MAX_ANGLE - deltaY * (MAX_ANGLE - MIN_ANGLE);

        vel.x = MathUtils.cos(angle) * (vel.x / Math.abs(vel.x));
        vel.y = MathUtils.sin(angle);

        ballSpeed = 500;
    }

    public void wallCollision() {
        vel.y *= -1;
    }
}
