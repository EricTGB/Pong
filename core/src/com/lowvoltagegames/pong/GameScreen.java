package com.lowvoltagegames.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lowvoltagegames.pong.ai.AiObserver;
import com.lowvoltagegames.pong.entity.GameObject;
import com.lowvoltagegames.pong.entity.GraphicsComponent;
import com.lowvoltagegames.pong.entity.PhysicsComponent;
import com.lowvoltagegames.pong.entity.ball.Ball;
import com.lowvoltagegames.pong.entity.ball.BallInput;
import com.lowvoltagegames.pong.entity.ball.BallPhysics;
import com.lowvoltagegames.pong.entity.generic.RectangleGraphics;
import com.lowvoltagegames.pong.entity.paddle.AiPaddleInput;
import com.lowvoltagegames.pong.entity.paddle.Paddle;
import com.lowvoltagegames.pong.entity.paddle.PaddleInput;
import com.lowvoltagegames.pong.entity.paddle.PaddlePhysics;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final Pong game;
    private final ArrayList<GameObject> objects;
    private final AiObserver observer;
    private final ShapeRenderer shape;
    private final GraphicsComponent graphics;

    public float width;
    public float height;

    public GameScreen(final Pong game) {
        this.game = game;

        this.shape = new ShapeRenderer();
        this.objects = new ArrayList<>();
        this.observer = new AiObserver();

        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        graphics = new RectangleGraphics();
        PhysicsComponent paddlePhysics = new PaddlePhysics();

        GameObject obj = new Paddle(new PaddleInput(), paddlePhysics, graphics);
        addObject(obj, obj.width * 5, height / 2 - obj.height / 2);

        obj = new Paddle(new AiPaddleInput(observer), paddlePhysics, graphics);
        addObject(obj, width - obj.width * 5, height / 2 - obj.height / 2);

        obj = new Ball(new BallInput(), new BallPhysics(), graphics);
        observer.attach(obj);
        addObject(obj, width / 2 - obj.width / 2, height / 2 - obj.height / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        for (GameObject obj : objects) {
            obj.update(this, delta);
        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (GameObject obj : objects) {
            obj.render(shape);
        }
        shape.end();
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
        shape.dispose();
    }

    public void resolveCollisions(GameObject obj1) {
        for (GameObject obj2 : objects) {
            if (obj1 != obj2) {
                if (obj1.x < obj2.x + obj2.width &&
                        obj1.x + obj1.width > obj2.x &&
                        obj1.y < obj2.y + obj2.height &&
                        obj1.y + obj1.height > obj2.y) {
                    obj1.collision(obj2);
                }
            }
        }
    }

    public void goalScored(GameObject ball) {
        objects.remove(ball);
        GameObject newBall = new Ball(new BallInput(), new BallPhysics(), graphics);
        observer.attach(newBall);
        addObject(newBall, width / 2 - newBall.width / 2, height / 2 - newBall.height / 2);
    }

    private void addObject(GameObject obj, float x, float y) {
        obj.x = x;
        obj.y = y;
        objects.add(obj);
    }
}

