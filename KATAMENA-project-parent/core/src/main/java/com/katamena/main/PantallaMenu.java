package com.katamena.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaMenu implements Screen {

    Main game;
    SpriteBatch batch;
    BitmapFont font;
    GlyphLayout layout;

    public PantallaMenu(Main game) {
        this.game = game;
        batch  = new SpriteBatch();
        font   = new BitmapFont();
        layout = new GlyphLayout();
        font.getData().setScale(1.8f);
        font.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int screenW = Gdx.graphics.getWidth();
        int screenH = Gdx.graphics.getHeight();

        batch.begin();

        String titulo = "Bienvenido al juego de Kata";
        layout.setText(font, titulo);
        float tituloX = (screenW - layout.width) / 2f;
        font.draw(batch, titulo, tituloX, screenH * 0.7f);

        String sub = "Pulsa ENTER para alucinar como si \n te hubieras tomado un hongo de esos :p";
        layout.setText(font, sub);
        float subX = (screenW - layout.width) / 2f;
        font.draw(batch, sub, subX, screenH * 0.5f);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new PantallaJuego(game, 1)); // aqui le paso el nivel
            dispose();
        }
    }

    @Override public void resize(int w, int h) {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}