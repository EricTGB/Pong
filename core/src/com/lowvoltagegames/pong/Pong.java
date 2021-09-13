package com.lowvoltagegames.pong;

import com.badlogic.gdx.Game;

public class Pong extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

}
