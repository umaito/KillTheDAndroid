package com.killthed.killthed;

/**
 * Created by Dylan on 22/05/2015.
 */
import com.killthed.framework.Game;
import com.killthed.framework.Graphics;
import com.killthed.framework.Graphics.ImageFormat;
import com.killthed.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {

        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.background = g.newImage("background.png", ImageFormat.RGB565);
        Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);

        Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
        Assets.imboss2 = g.newImage("boss2.png", ImageFormat.ARGB4444);
        Assets.imboss = g.newImage("imboss.png", ImageFormat.ARGB4444);




        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");


        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}