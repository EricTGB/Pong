package com.lowvoltagegames.pong.entity.ball;

import com.lowvoltagegames.pong.entity.GameObject;
import com.lowvoltagegames.pong.entity.GraphicsComponent;
import com.lowvoltagegames.pong.entity.InputComponent;
import com.lowvoltagegames.pong.entity.PhysicsComponent;

public class Ball extends GameObject {
    private final float WIDTH = 8;
    private final float HEIGHT = 8;
    private final float INITIAL_VELOCITY = 200;

    public Ball(InputComponent input, PhysicsComponent physics, GraphicsComponent graphics) {
        super(input, physics, graphics);
        this.width = WIDTH;
        this.height = HEIGHT;
        this.velocity = INITIAL_VELOCITY;
    }
}
