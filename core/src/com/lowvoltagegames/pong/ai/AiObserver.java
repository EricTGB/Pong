package com.lowvoltagegames.pong.ai;

import com.lowvoltagegames.pong.entity.GameObject;

public class AiObserver {
    private GameObject object;

    public AiObserver() {

    }

    public void attach(GameObject object) {
        this.object = object;
    }

    public GameObject getObject() {
        return object;
    }
}
