package com.killthed.killthed;

//import java.awt.Rectangle;
import android.graphics.Rect;

public class Projectile {
	private int x, y, speedY;
	private boolean visible;
	private Rect r;

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedY = 7;
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}

	public void update() {
		y += -speedY;
		r.set(x, y, 5, 5);
		if (y < 0 ) {
			visible = false;
			r = null;
		}
		if (y > 1){
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if(Rect.intersects(r,GameScreen.hb.r)){
			visible = false;
			if (GameScreen.hb.health > 0) {
				GameScreen.hb.health -= 1;
			}
			if (GameScreen.hb.health == 0) {
                GameScreen.hb.setCenterX(-100);
                GameScreen.score += 3;
				
			}
		}
		
		if (Rect.intersects(r,GameScreen.hb2.r)){
			visible = false;
			if (GameScreen.hb2.health > 0) {
                GameScreen.hb2.health -= 1;
			}
			if (GameScreen.hb2.health == 0) {
                GameScreen.hb2.setCenterX(-100);
                GameScreen.score += 3;
			}

		}
		if(Rect.intersects(r,GameScreen.hb3.r)){
			visible = false;
			if (GameScreen.hb3.health > 0) {
                GameScreen.hb3.health -= 1;
			}
			if (GameScreen.hb3.health == 0) {
                GameScreen.hb3.setCenterX(-100);
                GameScreen.score += 3;
				
			}
		}
		
		if (Rect.intersects(r,GameScreen.hb4.r)){
			visible = false;
			if (GameScreen.hb4.health > 0) {
                GameScreen.hb4.health -= 1;
			}
			if (GameScreen.hb4.health == 0) {
                GameScreen.hb4.setCenterX(-100);
                GameScreen.score += 3;
			}

		}
		if (Rect.intersects(r,GameScreen.boss1.r)){
			visible = false;
			if (GameScreen.boss1.health > 0) {
                GameScreen.boss1.health -= 1;
			}
			if (GameScreen.boss1.health == 0) {
                GameScreen.boss1.setCenterX(-100);
                GameScreen.score += 10;
			}

		}
		if (Rect.intersects(r,GameScreen.boss2.r)){
			visible = false;
			if (GameScreen.boss2.health > 0) {
                GameScreen.boss2.health -= 1;
			}
			if (GameScreen.boss2.health == 0) {
                GameScreen.boss2.setCenterX(-100);
                GameScreen.score += 10;
			}

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
