package com.lowvoltagegames.pong;

public class AiPlayer {
    private final int PADDLE_SPEED = 300;

    public void movePaddle(float delta, Paddle paddle, Ball ball) {
        float ballCentre = ball.y + ball.height / 2;
        float paddleCentre = paddle.y + paddle.height / 2;

        if (ballCentre - paddleCentre  >= 0) {
            paddle.y += Math.min(ballCentre - paddleCentre, PADDLE_SPEED * delta);
        } else {
            paddle.y -= Math.min(paddleCentre - ballCentre, PADDLE_SPEED * delta);
        }
    }
}
