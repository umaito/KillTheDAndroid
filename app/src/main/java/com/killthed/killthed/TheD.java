package com.killthed.killthed;

import android.graphics.Rect;
import java.util.ArrayList;

public class TheD {
	// In Java, Class Variables should be private so that only its methods can
	// change them.

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 699;

	private int centerX = 240;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingDown = false;
	private boolean movingUp = false;


	private boolean readyToFire = true;

	private static Boss1 boss1 = GameScreen.getBoss1();
	private static Boss2 boss2 = GameScreen.getBoss2();

	private static Background bg1 = GameScreen.getBg1();
	private static Background bg2 = GameScreen.getBg2();

	private int speedX = 0;
	private int speedY = 0;
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);

	public static Rect yellowRed = new Rect(0, 0, 0, 0);

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {


        // Moves Character or Scrolls Background accordingly.
		if (boss1 != null && (boss1.getCenterY() < 100 || boss1.getCenterX() == -100) && ( boss2.getCenterY() < 100 || boss2.getCenterX() == -100)) {
			if (speedY == 0 || speedY < 0) {
				bg1.setSpeedY(MOVESPEED);
				bg2.setSpeedY(MOVESPEED);

			}

			
			if (speedY == 10) {
				centerY += speedY;
				bg1.setSpeedY(MOVESPEED);
				bg2.setSpeedY(MOVESPEED);
			}
			if (centerY <= 810 && speedY > 0) {
				centerY += speedY;
				bg1.setSpeedY(MOVESPEED);
				bg2.setSpeedY(MOVESPEED);
			}

			if (speedY < 0 && centerY < 200) {
				bg1.setSpeedY(MOVESPEED);
				bg2.setSpeedY(MOVESPEED);
			}

			// Updates Y Position
			centerY += speedY;
			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				bg1.setSpeedY(MOVESPEED);
				bg2.setSpeedY(MOVESPEED);
			}
		} else
		{
			if (speedY == 0 || speedY < 0) {
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);

			}

			
			if (speedY == 10) {
				centerY += speedY;
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);
			}
			if (centerY <= 810 && speedY > 0) {
				centerY += speedY;
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);
			}

			if (speedY < 0 && centerY < 200) {
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);
			}

			// Updates Y Position
			centerY += speedY;
			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);
			}
		}
		
		if (speedX < 0) {
			centerX += speedX;
		}
		if (centerX <= 415 && speedX > 0) {
			centerX += speedX;
		}
		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		if (centerY + speedY <= 150) {
			centerY = 151;
		}

		rect.set(centerX - 25, centerY - 63, centerX + 25, centerY + 63);
		rect2.set(centerX - 35, rect.top + 63, centerX + 35, rect.top + 128);

		yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);

	}

	public void moveRight() {
		
			speedX = MOVESPEED;
		
	}

	public void moveLeft() {
		
			speedX = -MOVESPEED;
		
	}

	public void moveUp() {

		speedY = -MOVESPEED;

	}

	public void moveDown() {

		speedY = MOVESPEED;

	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stopUp() {
		setMovingUp(false);
		stop();
	}

	public void stopDown() {
		setMovingDown(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
		if (isMovingUp() == false && isMovingDown() == false) {
			speedY = 0;
		}

		if (isMovingUp() == false && isMovingDown() == true) {
			moveDown();
		}

		if (isMovingUp() == true && isMovingDown() == false) {
			moveUp();
		}

	}



	public void shoot() {
		if (boss2.getCenterX()> -100) {
			Projectile p = new Projectile(centerX, centerY - 25);
			projectiles.add(p);
		}
		else{
			Projectile b = new Projectile(centerX-10, centerY - 25);
			projectiles.add(b);
			Projectile p = new Projectile(centerX+10, centerY - 25);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}


	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}
}
