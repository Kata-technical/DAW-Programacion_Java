package com.katamena.main;

import com.badlogic.gdx.Game;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new PantallaMenu(this));  // arranca en el menú epicamente
    }
}