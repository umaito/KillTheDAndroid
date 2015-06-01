package com.killthed.killthed;

import android.graphics.Rect;
import java.util.ArrayList;

public class Enemy {

	private int power, speedY, centerX, centerY;
	private Background bg = GameScreen.getBg1();
	private TheD thed = GameScreen.getTheD();
	public Rect r = new Rect(0, 0, 0, 0);

	public ArrayList<BadProjectile> badprojectiles = new ArrayList<BadProjectile>();

	public void update() {

		centerY += speedY;
		speedY = bg.getSpeedY();
		r.set(centerX - 25, centerY - 25, 60, 60);
		if (Rect.intersects(r,TheD.yellowRed)) {
			checkCollision();
		}

	}

	private void checkCollision() {
		if (Rect.intersects(r,TheD.rect) || Rect.intersects(r,TheD.rect2)) {
			System.out.println("collision");
			thed.setCenterX(centerX + 800);
		}
	}

	public void follow() {

	}

	public void die() {
	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public ArrayList getBadProjectiles() {
		return badprojectiles;
	}

}