package com.killthed.killthed;

import android.graphics.Rect;

public class BadProjectile {
	private int x, y, speedY;
	private boolean visible;
	private Rect r;
	private TheD thed = GameScreen.getTheD();

	public BadProjectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedY = -7;
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}

	public void update() {
		y += -speedY;
		r.set(x, y, x+7, y+10);
		if (y < 0 ) {
			visible = false;
			r = null;
		}
		if (y > 1){
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if(Rect.intersects(r,TheD.rect) || Rect.intersects(r,TheD.rect2)){
			thed.setCenterX(800);
				
			}
		}
		

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
