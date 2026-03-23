package com.katamena.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class PantallaJuego implements Screen {

    Main game;
    int  nivel;

    ShapeRenderer shape;

    float x, y;
    float width  = 40;
    float height = 50;

    float velocityX = 0;
    float velocityY = 0;
    float gravity   = -800f;
    float jumpForce =  600f;
    float speed     =  250f;
    boolean onGround = false;

    OrthographicCamera camera;
    FitViewport viewport;

    ArrayList<Rectangle> plataformas;
    ArrayList<Rectangle> peligros;

    float respawnX = 100;
    float respawnY = 100;

    BitmapFont font;
    SpriteBatch batch;
    int muertes = 0;

    Rectangle puerta;

    public PantallaJuego(Main game, int nivel) {
        this.game  = game;
        this.nivel = nivel;
    }

    @Override
    public void show() {
        shape    = new ShapeRenderer();
        batch    = new SpriteBatch();
        font     = new BitmapFont();
        camera   = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);

        camera.position.set(400, 240, 0);
        camera.update();

        font.getData().setScale(2f);
        font.setColor(Color.WHITE);

        x = respawnX;
        y = respawnY;

        cargarNivel(nivel);
    }

    void cargarNivel(int n) {
        plataformas = new ArrayList<>();
        peligros    = new ArrayList<>();
        puerta      = null;

        if (n == 1) {
            plataformas.add(new Rectangle(0,    0,   2000, 50));
            plataformas.add(new Rectangle(300, 150,  150,  20));
            plataformas.add(new Rectangle(550, 250,  150,  20));
            plataformas.add(new Rectangle(800, 150,  200,  20));
            plataformas.add(new Rectangle(1000,  0,   30, 300));
            plataformas.add(new Rectangle(1100, 300,  150,  20));

            peligros.add(new Rectangle(450, 50, 80, 30));
            peligros.add(new Rectangle(900, 50, 80, 30));

            puerta = new Rectangle(1800, 50, 40, 80);
        }

        if (n == 2) {
            plataformas.add(new Rectangle(0,    0,   300, 50));
            plataformas.add(new Rectangle(500,  0,   200, 50));
            plataformas.add(new Rectangle(900,  0,   150, 50));
            plataformas.add(new Rectangle(1200, 0,   400, 50));
            plataformas.add(new Rectangle(280,  120, 120, 20));
            plataformas.add(new Rectangle(480,  220, 120, 20));
            plataformas.add(new Rectangle(680,  120, 120, 20));
            plataformas.add(new Rectangle(880,  300, 150, 20));
            plataformas.add(new Rectangle(1050, 180, 100, 20));
            plataformas.add(new Rectangle(1350,   0,  30, 200));
            plataformas.add(new Rectangle(1450, 100,  30, 250));

            peligros.add(new Rectangle(300,  0,  200, 30));
            peligros.add(new Rectangle(700,  0,  200, 30));
            peligros.add(new Rectangle(1050, 0,  150, 30));
            peligros.add(new Rectangle(600, 220,  60, 20));
            peligros.add(new Rectangle(900,  50,  80, 30));

            puerta = new Rectangle(1550, 50, 40, 80);
        }
        
        if (plataformas.isEmpty()) {
            game.setScreen(new PantallaMenu(game));
            dispose();
        }
    }

    @Override
    public void render(float delta) {

        // ---- LÓGICA ----

        velocityY += gravity * delta;

        velocityX = 0;
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) velocityX =  speed;
        if (Gdx.input.isKeyPressed(Keys.LEFT)  || Gdx.input.isKeyPressed(Keys.A)) velocityX = -speed;

        if ((Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.W)) && onGround) {
            velocityY = jumpForce;
            onGround  = false;
        }

        // Colisiones horizontales
        x += velocityX * delta;
        Rectangle jugador = new Rectangle(x, y, width, height);
        for (Rectangle p : plataformas) {
            if (jugador.overlaps(p)) {
                if (velocityX > 0) x = p.x - width;
                else if (velocityX < 0) x = p.x + p.width;
                jugador.x = x;
            }
        }

        // Colisiones verticales
        y += velocityY * delta;
        jugador.y = y;
        onGround = false;
        for (Rectangle p : plataformas) {
            if (jugador.overlaps(p)) {
                if (velocityY <= 0) {
                    y = p.y + p.height;
                    velocityY = 0;
                    onGround  = true;
                } else if (velocityY > 0) {
                    y = p.y - height;
                    velocityY = 0;
                }
                jugador.y = y;
            }
        }

        // Muerte por caída
        if (y < -200) morir();

        // Muerte por peligros
        Rectangle jugador2 = new Rectangle(x, y, width, height);
        for (Rectangle p : peligros) {
            if (jugador2.overlaps(p)) { morir(); break; }
        }

        // Puerta al siguiente nivel
        if (puerta != null && jugador2.overlaps(puerta)) {
            siguienteNivel();
        }

        // ---- DIBUJADO ----
        Gdx.gl.glClearColor(0.3f, 0.6f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(x + width / 2, 240, 0);
        camera.update();
        shape.setProjectionMatrix(camera.combined);

        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.setColor(Color.GREEN);
        for (Rectangle p : plataformas) shape.rect(p.x, p.y, p.width, p.height);

        shape.setColor(Color.RED);
        for (Rectangle p : peligros) shape.rect(p.x, p.y, p.width, p.height);

        shape.setColor(Color.YELLOW);
        if (puerta != null) shape.rect(puerta.x, puerta.y, puerta.width, puerta.height);

        shape.setColor(Color.BLUE);
        shape.rect(x, y, width, height);

        shape.end();

        batch.begin();
        font.draw(batch, "Nivel: "   + nivel,   20, Gdx.graphics.getHeight() - 20);
        font.draw(batch, "Muertes: " + muertes, 20, Gdx.graphics.getHeight() - 55);
        font.draw(batch, "Aaron puta", 80, 40);
        batch.end();
    }

    private void morir() {
        muertes++;
        x = respawnX; y = respawnY;
        velocityX = 0; velocityY = 0;
        onGround = false;
    }

    private void siguienteNivel() {
        if (nivel + 1 > 2) {  // cambia el 2 por el número máximo de niveles que tengas
            game.setScreen(new PantallaMenu(game));  // vuelve al menú si no hay más niveles
        } else {
            game.setScreen(new PantallaJuego(game, nivel + 1));
        }
        dispose();
    }

    @Override public void resize(int w, int h) { viewport.update(w, h); }
    @Override public void pause()  { }
    @Override public void resume() { }
    @Override public void hide()   { }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
        font.dispose();
    }
}