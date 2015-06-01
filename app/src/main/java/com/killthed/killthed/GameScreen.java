package com.killthed.killthed;

/**
 * Created by Dylan on 22/05/2015.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import android.graphics.Color;
import android.graphics.Paint;

import com.killthed.framework.Game;
import com.killthed.framework.Graphics;
import com.killthed.framework.Image;
import com.killthed.framework.Input.TouchEvent;
import com.killthed.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup

    private static Background bg1, bg2;
    private static TheD thed;
    public static Heliboy hb, hb2, hb3, hb4;
    public static Boss1 boss1;
    public static Boss2 boss2;
    public static int score = 0;
    private Image character, heliboy, imboss, imboss2;



    int livesLeft = 1;
    Paint paint, paint2;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here
        float randomY1 = (float) Math.random() * 700;
        float randomX1 = 50 + (float) Math.random() * 350;
        float randomY2 = (float) Math.random() * 700;
        float randomX2 = 50 + (float) Math.random() * 350;
        float randomY3 = (float) Math.random() * 700;
        float randomX3 = 50 + (float) Math.random() * 350;
        float randomY4 = (float) Math.random() * 700;
        float randomX4 = 50 + (float) Math.random() * 350;

        bg1 = new Background(0, -1080);
        bg2 = new Background(0, -3100);
        boss1 = new Boss1(225, -8000);
        boss2 = new Boss2(50, -16000);
        thed = new TheD();
        hb = new Heliboy((int) randomX1, -(int) randomY1);
        hb2 = new Heliboy((int) randomX2, -(int) randomY2);
        hb3 = new Heliboy((int) randomX3, -(int) randomY3);
        hb4 = new Heliboy((int) randomX4, -(int) randomY4);

        character = Assets.character;


        heliboy = Assets.heliboy;



        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

    }


    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List touchEvents, float deltaTime) {

        int t = 0;
        int b = 0;
        int b2 = 0;
        int n = 0;
        int h = 50;

        if (state == GameState.Running) {


                float randomX1 = 50 + (float) Math.random() * 350;

                float randomX2 = 50 + (float) Math.random() * 350;

                float randomX3 = 50 + (float) Math.random() * 350;

                float randomX4 = 50 + (float) Math.random() * 350;
                thed.update();
                hb.update();
                hb2.update();
                hb3.update();
                hb4.update();
                boss1.update();
                boss2.update();

                if (thed.getCenterY() - boss1.getCenterY() > 1400
                        || boss1.getCenterX() == -100
                        && (thed.getCenterY() - boss2.getCenterY() > 1100 || boss2
                        .getCenterX() == -100)) {
                    if (hb.getCenterX() == -100 || hb.getCenterY() > 800) {
                        hb.setCenterX((int) randomX1);
                        hb.setCenterY(thed.getCenterY() - 800);
                        hb.health = 3;

                    }
                    if (hb2.getCenterX() == -100 || hb2.getCenterY() > 800) {
                        hb2.setCenterX((int) randomX2);
                        hb2.setCenterY(thed.getCenterY() - 800);
                        hb2.health = 3;
                    }
                    if (hb3.getCenterX() == -100 || hb3.getCenterY() > 800) {
                        hb3.setCenterX((int) randomX3);
                        hb3.setCenterY(thed.getCenterY() - 800);
                        hb3.health = 3;
                    }
                    if (hb4.getCenterX() == -100 || hb4.getCenterY() > 800) {
                        hb4.setCenterX((int) randomX4);
                        hb4.setCenterY(thed.getCenterY() - 800);
                        hb4.health = 3;
                    }
                }
                bg1.update();
                bg2.update();

                if (boss2.getCenterY() > 10 & boss2.getCenterX() != -100) {

                    h = h + 1;
                    if (h >= 50 && h < 390) {
                        boss2.setCenterX(boss2.getCenterX() + 1);
                    } else if (h >= 390 && h < 730) {
                        boss2.setCenterX(boss2.getCenterX() - 1);
                    } else if (h == 730) {
                        h = 50;
                    }
                }

                t = t + 1;

                if (t == 10) {
                    thed.shoot();
                    t = 0;
                }

                if (boss1.getCenterY() > 0) {
                    b = b + 1;
                    if (b == 60) {
                        boss1.shoot();
                        b = 0;
                    }
                }
                if (boss2.getCenterY() > 0) {
                    b2 = b2 + 1;
                    if (b2 == 60) {
                        boss2.shoot();
                        b2 = 0;
                    }
                }

                if (boss2.getCenterX() == -100) {
                    n = n + 1;
                    if (n == 60) {
                        hb4.shoot();
                        hb2.shoot();
                        n = 0;
                    }
                }



                if (thed.getCenterX() == -100) {
                    state = GameState.GameOver;
                }

                ArrayList projectiles = thed.getProjectiles();
                for (int i = 0; i < projectiles.size(); i++) {
                    Projectile p = (Projectile) projectiles.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        projectiles.remove(i);
                    }
                }
                ArrayList badprojectiles = boss1.getBadProjectiles();
                for (int i = 0; i < badprojectiles.size(); i++) {
                    BadProjectile p = (BadProjectile) badprojectiles.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        badprojectiles.remove(i);
                    }
                }
                ArrayList badprojectiles2 = boss2.getBadProjectiles();
                for (int i = 0; i < badprojectiles2.size(); i++) {
                    BadProjectile p = (BadProjectile) badprojectiles2.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        badprojectiles2.remove(i);
                    }
                }
                ArrayList badprojectiles3 = hb2.getBadProjectiles();
                for (int i = 0; i < badprojectiles3.size(); i++) {
                    BadProjectile p = (BadProjectile) badprojectiles3.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        badprojectiles3.remove(i);
                    }
                }
                ArrayList badprojectiles4 = hb4.getBadProjectiles();
                for (int i = 0; i < badprojectiles4.size(); i++) {
                    BadProjectile p = (BadProjectile) badprojectiles4.get(i);
                    if (p.isVisible() == true) {
                        p.update();
                    } else {
                        badprojectiles4.remove(i);
                    }
                }

            }

        }




    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    private void updatePaused(List touchEvents) {
        /*int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 240)) {

                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }*/
    }

    private void updateGameOver(List touchEvents) {
        /*int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }*/

    }



    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());


        ArrayList projectiles = thed.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
        }
        // First draw the game elements.


        g.drawImage(heliboy, hb.getCenterX() - 48,
                hb.getCenterY() - 48);
        g.drawImage(heliboy, hb2.getCenterX() - 48,
                hb2.getCenterY() - 48);
        g.drawImage(heliboy, hb3.getCenterX() - 48,
                hb3.getCenterY() - 48);
        g.drawImage(heliboy, hb4.getCenterX() - 48,
                hb4.getCenterY() - 48);
        g.drawImage(imboss, boss1.getCenterX() - 48,
                boss1.getCenterY() - 48);
        g.drawImage(imboss2, boss2.getCenterX() - 48,
                boss2.getCenterY() - 48);

        // g.drawRect((int)thed.rect.getX(), (int)thed.rect.getY(),
        // (int)thed.rect.getWidth(), (int)thed.rect.getHeight());
        // g.drawRect((int)thed.rect2.getX(), (int)thed.rect2.getY(),
        // (int)thed.rect2.getWidth(), (int)thed.rect2.getHeight());
        g.drawImage(character, thed.getCenterX() - 50,
                thed.getCenterY() - 75);
        /*g.setFont( 25);
        g.setColor(Color.GRAY);
        g.drawString("" + playerScore /100, 20, 30);*/

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }



    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        thed = null;
        hb = null;
        hb2 = null;
        hb3 = null;
        hb4 = null;
        boss1 = null;
        boss2 = null;

        character = null;

        heliboy = null;


        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    public static Background getBg1() {
        // TODO Auto-generated method stub
        return bg1;
    }

    public static Background getBg2() {
        // TODO Auto-generated method stub
        return bg2;
    }

    public static TheD getTheD() {
        // TODO Auto-generated method stub
        return thed;
    }
    public static Boss1 getBoss1() {
        return boss1;
    }


    public static Boss2 getBoss2() {
        return boss2;
    }


}