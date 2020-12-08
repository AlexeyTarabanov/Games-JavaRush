package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;

public class Game2048 extends Game {

    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();

    }

    private void drawScene() {

        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[y].length; x++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber() {

        if (getMaxTileValue() >= 2048)
            win();

        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);

        int random = getRandomNumber(10);

        // проверяем не занята ли ячейка другим числом
        if (gameField[x][y] == 0) {
            if (random == 9) {
                gameField[x][y] = 4;
            } else {
                gameField[x][y] = 2;
            }
        } else {
            createNewNumber();
        }
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.TRANSPARENT;
            case 2:
                return Color.LIGHTGOLDENRODYELLOW;
            case 4:
                return Color.LIGHTCORAL;
            case 8:
                return Color.LIGHTSEAGREEN;
            case 16:
                return Color.DARKORANGE;
            case 32:
                return Color.GREENYELLOW;
            case 64:
                return Color.LIGHTGREEN;
            case 128:
                return Color.CORNFLOWERBLUE;
            case 256:
                return Color.VIOLET;
            case 512:
                return Color.TOMATO;
            case 1024:
                return Color.MAGENTA;
            case 2048:
                return Color.MEDIUMVIOLETRED;
            default:
                return Color.NONE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        // красить клетки будем с помощью метода getColorByValue
        Color color = getColorByValue(value);
        if (value == 0) {
            setCellValueEx(x, y, color, "");
        } else {
            setCellValueEx(x, y, color, Integer.toString(value));
        }
    }

    private boolean compressRow(int[] row) {
        // сдвигает все ненулевые элементы массива row влево (в сторону нулевого индекса),
        // а нулевые элементы переносит вправо.
        int[] copyMas = Arrays.copyOf(row, row.length);
        for (int i = 0; i < row.length; i++) {
            int start = row[i];
            int startIndex = i;
            for (int j = i; j < row.length; j++) {
                if (row[j] != 0) {
                    start = row[j];
                    startIndex = j;
                    break;
                }
            }

            int temp = row[i];
            row[i] = start;
            row[startIndex] = temp;
        }
        return !Arrays.equals(row, copyMas);
    }

    private boolean mergeRow(int[] row) {
        boolean result = false;

        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] > 0) {
                if (row[i] == row[i + 1]) {
                    row[i] = (row[i] + row[i + 1]);
                    row[i+1] = 0;
                    setScore(score += row[i]);
                    //score += row[i];
                    result = true;
                }
            }
        }

        return result;
    }

    @Override
    public void onKeyPress(Key key) {

        if (key == Key.SPACE) {
            isGameStopped = false;
            drawScene();
            createGame();
            setScore(score = 0);
        }

        if (isGameStopped & key != Key.SPACE) {
            return;
        }

        if (!canUserMove()) {
            gameOver();
            return;
        }

        if (key == Key.LEFT) {
            moveLeft();
            drawScene();
        } else if (key == Key.RIGHT) {
            moveRight();
            drawScene();
        } else if (key == Key.UP) {
            moveUp();
            drawScene();
        } else if (key == Key.DOWN){
            moveDown();
            drawScene();
        }
    }

    private void moveLeft() {

        boolean flag = false;
        for (int i = 0; i < SIDE; i++) {
            if (compressRow(gameField[i]))
                flag = true;
            if (mergeRow(gameField[i]))
                flag = true;
            if (compressRow(gameField[i]))
                flag = true;
        }
        if (flag)
            createNewNumber();

    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }
    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }
    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] temp = new int[SIDE][SIDE];
        for (int i = 0; i < temp.length; i++) {
            int y = 3;
            for (int j = 0; j < temp.length; j++) {
                temp[i][j] = gameField[y][i];
                y -= 1;
            }
        }
        gameField = temp;
    }

    private int getMaxTileValue() {
        int max = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] > max) {
                    max = gameField[i][j];
                }
            }
        }
        return max;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "Ты выиграл!", Color.BLACK, 75);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "Game Over", Color.BLACK, 75);
    }

    private boolean canUserMove() {
        // возвращает true, если кол-во нулевых эл-тов больше нуля
        // возвращать true, если нулевых элементов нет,
        // но в матрице gameField есть хотя бы две соседние клетки с одинаковым значением (по горизонтали или вертикали).
        int count = 0;
        boolean flag = false;

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[i][j] == 0) {
                    count++;
                }
            }
        }

        if (count > 0) {
            flag = true;
        }

        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < gameField.length; i++) {
                for (int j = 0; j < gameField.length - 1; j++) {
                    if (gameField[i][j] == gameField[i][j + 1]) {
                        flag = true;
                    }
                }
            }
            rotateClockwise();
        }

        return flag;
    }
}



