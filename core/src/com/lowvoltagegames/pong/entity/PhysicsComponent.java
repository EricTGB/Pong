package com.lowvoltagegames.pong.entity;

import com.lowvoltagegames.pong.GameScreen;

public interface PhysicsComponent {
    void update(GameObject obj, GameScreen screen, float delta);

    void collision(GameObject obj, GameObject collisionObj);
}
