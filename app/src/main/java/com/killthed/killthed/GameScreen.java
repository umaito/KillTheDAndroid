package com.killthed.killthed;

/**
 * Created by Dylan on 22/05/2015.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.view.MotionEvent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.VelocityTracker;

import com.killthed.framework.Game;
import com.killthed.framework.Graphics;
import com.killthed.framework.Image;
import com.killthed.framework.Input.TouchEvent;
import com.killthed.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver, Win
    }

    GameState state = GameState.Ready;

    // Variable Setup


    private static Background bg1, bg2;
    private static TheD thed;
    public static Heliboy hb, hb2, hb3, hb4;
    public static Boss1 boss1;
    public static Boss2 boss2;
    public static Boss3 boss3;
    public static Boss4 boss4;
    public static int score = 0;
    int t = 0;
    int b = 0;
    int b2 = 0;
    int b3 = 0;
    int b4 = 0;
    int n = 0;
    int h = 50;
    int h2 = 50;
    int h3 = 50;
    private Image character, heliboy, imboss, imboss2, imboss3, imboss4;



    int livesLeft = 1;
    Paint paint, paint2, paint3;

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
        boss1 = new Boss1(240, -8000);
        boss2 = new Boss2(50, -16000);
        boss3 = new Boss3(50, -25000);
        boss4 = new Boss4(50, -34000);
        thed = new TheD();
        hb = new Heliboy((int) randomX1, -(int) randomY1);
        hb2 = new Heliboy((int) randomX2, -(int) randomY2);
        hb3 = new Heliboy((int) randomX3, -(int) randomY3);
        hb4 = new Heliboy((int) randomX4, -(int) randomY4);

        character = Assets.character;


        heliboy = Assets.heliboy;
        imboss = Assets.imboss;
        imboss2 = Assets.imboss2;
        imboss3 = Assets.imboss3;
        imboss4 = Assets.imboss4;
        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(80);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        paint3 = new Paint();
        paint3.setTextSize(60);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.YELLOW);


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
        if (state == GameState.Win)
            updateWin(touchEvents);
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;

    }

    private void updateWin(List touchEvents){
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent)touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    private void updateRunning(List touchEvents, float deltaTime) {

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent)touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {

                if (event.x > thed.getCenterX() + 25) {
                    thed.moveRight();
                }else if (event.x < thed.getCenterX()+25 && event.x > thed.getCenterX() -25){
                        thed.setCenterX(event.x);
                } else if(event.x < thed.getCenterX() -25){
                    thed.moveLeft();
                }

                if (event.y > thed.getCenterY() +30) {
                    thed.moveDown();
                }else if (event.y > thed.getCenterY()-30 && event.y < thed.getCenterY() +30){
                    thed.setCenterY(event.y);
                 } else if(event.y < thed.getCenterY() -30){
                    thed.moveUp();
                }
            }

            if (event.type == TouchEvent.TOUCH_DRAGGED) {

                if (event.x > thed.getCenterX() + 30) {
                    thed.moveRight();
                }else if (event.x < thed.getCenterX()+30 && event.x > thed.getCenterX() -30){
                    thed.stopRight();
                    thed.stopLeft();
                } else if(event.x < thed.getCenterX() -30){
                    thed.moveLeft();
                }

                if (event.y > thed.getCenterY() +30) {
                    thed.moveDown();
                } else if(event.y < thed.getCenterY() -30){
                    thed.moveUp();
                }else if (event.y > thed.getCenterY()-30 && event.y < thed.getCenterY() +30){
                    thed.setCenterY(event.y);
                }

            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 35, 35)) {
                    pause();
                }

                if (event.x > thed.getCenterX() +25) {
                    // Move right.
                    thed.stopRight();
                }else if (event.x < thed.getCenterX()+25 && event.x > thed.getCenterX() -25){
                    thed.setCenterX(event.x);
                } else if(event.x < thed.getCenterX() -25){
                    thed.stopLeft();
                }

                if (event.y > thed.getCenterY() +30 ) {
                    thed.stopDown();
                }else if (event.y > thed.getCenterY()-30 && event.y < thed.getCenterY() +30){
                    thed.setCenterY(event.y);
                } else if(event.y < thed.getCenterY() -30){
                    thed.stopUp();
                }
            }

        }

        if (state == GameState.Running) {

            float randomX1 = 50 + (float) Math.random() * 350;

            float randomX2 = 50 + (float) Math.random() * 350;

            float randomX3 = 50 + (float) Math.random() * 350;

            float randomX4 = 50 + (float) Math.random() * 350;
            boss1.update();
            boss2.update();
            boss3.update();
            boss4.update();
            thed.update();
            bg1.update();
            bg2.update();
            hb.update();
            hb2.update();
            hb3.update();
            hb4.update();


            if (thed.getCenterY() - boss1.getCenterY() > 1400
                    || boss1.getCenterX() == -100
                    && (thed.getCenterY() - boss2.getCenterY() > 1400 || boss2
                    .getCenterX() == -100)
                    && (thed.getCenterY() - boss3.getCenterY() > 1400 || boss3
                    .getCenterX() == -100)
                    && (thed.getCenterY() - boss4.getCenterY() > 1400 || boss4
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

            if (boss3.getCenterY() > 10 & boss3.getCenterX() != -100) {

                h2 = h2 + 1;
                if (h2 >= 50 && h2 < 390) {
                    boss3.setCenterX(boss3.getCenterX() + 1);
                } else if (h2 >= 390 && h2 < 730) {
                    boss3.setCenterX(boss3.getCenterX() - 1);
                } else if (h2 == 730) {
                    h2 = 50;
                }
            }

            if (boss4.getCenterY() > 10 & boss4.getCenterX() != -100) {

                h3 = h3 + 1;
                if (h3 >= 50 && h3 < 390) {
                    boss4.setCenterX(boss4.getCenterX() + 1);
                } else if (h3 >= 390 && h3 < 730) {
                    boss4.setCenterX(boss4.getCenterX() - 1);
                } else if (h3 == 730) {
                    h3 = 50;
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

            if (boss3.getCenterY() > 0) {
                b3 = b3 + 1;
                if (b3 == 60) {
                    boss3.shoot();
                    b3 = 0;
                }
            }
            if (boss4.getCenterY() > 0) {
                b4 = b4 + 1;
                if (b4 == 60) {
                    boss4.shoot();
                    b4 = 0;
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

            if (thed.getCenterX() > 600) {
                state = GameState.GameOver;
            }

            if (boss4.getCenterX() < -50) {
                state = GameState.Win;
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
            ArrayList badprojectiles5 = boss3.getBadProjectiles();
            for (int i = 0; i < badprojectiles5.size(); i++) {
                BadProjectile p = (BadProjectile) badprojectiles5.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                    badprojectiles5.remove(i);
                }
            }
            ArrayList badprojectiles6 = boss4.getBadProjectiles();
            for (int i = 0; i < badprojectiles6.size(); i++) {
                BadProjectile p = (BadProjectile) badprojectiles6.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                    badprojectiles6.remove(i);
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

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent)touchEvents.get(i);
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
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent)touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }



    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());


        ArrayList projectiles = thed.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.drawRect(p.getX(), p.getY(), 10, 10, Color.YELLOW);
        }
        ArrayList badprojectiles = boss1.getBadProjectiles();
        for (int i = 0; i < badprojectiles.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
        }
        ArrayList badprojectiles2 = boss2.getBadProjectiles();
        for (int i = 0; i < badprojectiles2.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles2.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
        }
        ArrayList badprojectiles3 = hb2.getBadProjectiles();
        for (int i = 0; i < badprojectiles3.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles3.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
        }
        ArrayList badprojectiles4 = hb4.getBadProjectiles();
        for (int i = 0; i < badprojectiles4.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles4.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
        }
        ArrayList badprojectiles5 = boss3.getBadProjectiles();
        for (int i = 0; i < badprojectiles5.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles5.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
        }

        ArrayList badprojectiles6 = boss4.getBadProjectiles();
        for (int i = 0; i < badprojectiles6.size(); i++) {
            BadProjectile p = (BadProjectile) badprojectiles6.get(i);
            g.drawRect(p.getX(), p.getY(), 7, 10, Color.RED);
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
        g.drawImage(imboss3, boss3.getCenterX() - 48,
                boss3.getCenterY() - 48);
        g.drawImage(imboss4, boss4.getCenterX() - 48,
                boss4.getCenterY() - 48);

        g.drawImage(character, thed.getCenterX() - 50,
                thed.getCenterY() - 75);

        g.drawString(Integer.toString(score), 400, 50, paint);


        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
        if (state == GameState.Win)
            drawWinUI();
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
        boss3 = null;
        boss4 = null;
        score = 0;

        character = null;

        heliboy = null;


        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Let's GO!", 240, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Continuer", 240, 165, paint2);
        g.drawString("Menu", 240, 360, paint2);

    }
    private void drawWinUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1500, 1500, Color.BLACK);
        g.drawString("Vous avez survécu", 240, 240, paint3);
        g.drawString("L'opération ''Kill The D''", 240, 290, paint);
        g.drawString("est un échec", 240, 320, paint);
        g.drawString("Bravo", 240, 500, paint2);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1500, 1500, Color.BLACK);
        g.drawString("You are dead", 240, 240, paint2);
        g.drawString("Vous êtes un perdant", 240, 290, paint);
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

    public static Boss3 getBoss3() {
        return boss3;
    }

    public static Boss4 getBoss4() {
        return boss4;
    }
}