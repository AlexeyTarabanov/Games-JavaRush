package com.javarush.games.spaceinvaders;

/*1. В классе SpaceInvadersGame должен существовать приватный метод void createGame().
2. В классе SpaceInvadersGame должен существовать приватный метод void drawScene().
3. В классе SpaceInvadersGame должен существовать приватный метод void drawField().
4. В методе drawScene() должен быть вызван метод drawField().
5. В методе createGame() должен быть вызван метод drawScene().
6. В методе initialize() должен быть вызван метод createGame().
7. В методе initialize() метод createGame() должен вызываться после setScreenSize(int, int).*/

import com.javarush.engine.cell.*;

public class SpaceInvadersGame extends Game {
    //1. создали класс SpaceInvadersGame заэкстендились от Game
    //2. создал перемееные WIDTH, HEIGHT сразу проинициализировали его
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    //3. переопредил метод initialize
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        //9. вызвал метод createGame()
        createGame();
    }
    //4. создал метод createGame
    //8. вызвал метод drawScene
    private void createGame() {
        drawScene();
    }
    //5. создал метод drawScene
    //7. вызвал метод drawField()
    private void drawScene() {
        drawField();
    }
    //6. создал метод drawField
    private void drawField() {}

}