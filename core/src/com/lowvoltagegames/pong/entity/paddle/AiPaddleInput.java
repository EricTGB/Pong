package com.lowvoltagegames.pong.entity.paddle;

import com.lowvoltagegames.pong.ai.AiObserver;
import com.lowvoltagegames.pong.entity.GameObject;
import com.lowvoltagegames.pong.entity.InputComponent;

public class AiPaddleInput implements InputComponent {
    private final int PADDLE_SPEED = 300;
    private AiObserver observer;

    public AiPaddleInput(AiObserver observer) {
        this.observer = observer;
    }

    @Override
    public void update(GameObject obj) {
        GameObject ball = observer.getObject();

        if (ball.y > obj.y + obj.height * 3 / 4) {
            obj.velocity = PADDLE_SPEED;
        } else if (ball.y + ball.height < obj.y + obj.height / 4) {
            obj.velocity = PADDLE_SPEED * -1;
        } else {
            obj.velocity = 0;
        }
    }
}