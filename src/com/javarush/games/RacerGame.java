package com.javarush.games;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public final int WIDTH = 64;
    public final int HEIGHT = 64;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
    }
}
