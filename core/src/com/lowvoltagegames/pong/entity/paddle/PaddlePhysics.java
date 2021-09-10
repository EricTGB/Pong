package com.lowvoltagegames.pong.entity.paddle;

import com.lowvoltagegames.pong.GameScreen;
import com.lowvoltagegames.pong.entity.GameObject;
import com.lowvoltagegames.pong.entity.PhysicsComponent;

public class PaddlePhysics implements PhysicsComponent {
    @Override
    public void update(GameObject obj, GameScreen screen, float delta) {
        obj.y += obj.velocity * delta;

        if (obj.y + obj.height > screen.height) {
            obj.y = screen.height - obj.height;
        } else if (obj.y < 0) {
            obj.y = 0;
        }

        screen.resolveCollisions(obj);
    }

    @Override
    public void collision(GameObject obj, GameObject collisionObj) {

    }
}
