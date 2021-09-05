package com.lowvoltagegames.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final int PADDLE_SPEED = 500;
    private final int PLAYER = 0;
    private final int ENEMY = 1;

    final Pong game;

    Texture paddleImage;
    Texture ballImage;
    Paddle[] paddles;
    Ball ball;
    AiPlayer ai;

    float screenWidth;
    float screenHeight;

    public GameScreen(final Pong game) {
        this.game = game;

        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

        paddles = new Paddle[2];
        for (int i = 0; i < paddles.length; i++) {
            paddles[i] = new Paddle();
        }
        ball = new Ball();
        ai = new AiPlayer();

        paddles[PLAYER].x = paddles[PLAYER].width * 4;
        paddles[PLAYER].y = screenHeight / 2 - paddles[PLAYER].height / 2;

        paddles[ENEMY].x = screenWidth - paddles[ENEMY].width * 5;
        paddles[ENEMY].y = screenHeight / 2 - paddles[ENEMY].height / 2;

        ball.x = screenWidth / 2 - ball.width / 2;
        ball.y = screenHeight / 2 - ball.height / 2;

        paddleImage = TextureUtils.rectangle(paddles[PLAYER]);
        ballImage = TextureUtils.rectangle(ball);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(paddleImage, paddles[PLAYER].x, paddles[PLAYER].y);
        game.batch.draw(paddleImage, paddles[ENEMY].x, paddles[ENEMY].y);
        game.batch.draw(ballImage, ball.x, ball.y);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            paddles[PLAYER].y += PADDLE_SPEED * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            paddles[PLAYER].y -= PADDLE_SPEED * delta;
        }

        ai.movePaddle(delta, paddles[ENEMY], ball);

        ball.updatePos(delta);

        for (Paddle paddle : paddles) {
            if (paddle.y + paddle.height > screenHeight) paddle.y = screenHeight - paddle.height;
            if (paddle.y < 0) paddle.y = 0;
            if (paddle.overlaps(ball)) ball.onHit(paddle);
        }

        if (ball.x <= 0 || ball.x >= screenWidth) {
            ball.x = screenWidth / 2 - ball.width / 2;
            ball.y = screenHeight / 2 - ball.height / 2;
            ball.initAngle();
        }

        if (ball.y >= screenHeight || ball.y <= 0) {
            ball.wallCollision();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ballImage.dispose();
        paddleImage.dispose();
    }
}

