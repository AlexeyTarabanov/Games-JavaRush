package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake extends GameObject {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x, y);
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void setDirection(Direction direction) {

        if (direction == Direction.DOWN && this.direction != Direction.UP && snakeParts.get(0).x != snakeParts.get(1).x) {
            this.direction = direction;
        } else if (direction == Direction.UP && this.direction != Direction.DOWN && snakeParts.get(0).x != snakeParts.get(1).x) {
            this.direction = direction;
        } else if (direction == Direction.RIGHT && this.direction != Direction.LEFT && snakeParts.get(0).y != snakeParts.get(1).y) {
            this.direction = direction;
        } else if (direction == Direction.LEFT && this.direction != Direction.RIGHT && snakeParts.get(0).y != snakeParts.get(1).y) {
            this.direction = direction;
        }
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            x = snakeParts.get(i).x;
            y = snakeParts.get(i).y;
            Color colorSnake = isAlive ? Color.AQUA : Color.RED;

            if (i != 0) {
                game.setCellValueEx(x, y, Color.NONE, BODY_SIGN, colorSnake, 75);
            } else {
                game.setCellValueEx(x, y, Color.NONE, HEAD_SIGN, colorSnake, 75);
            }
        }

    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();

        if (newHead.x >= SnakeGame.WIDTH || newHead.x < 0 ||
                newHead.y >= SnakeGame.HEIGHT || newHead.y < 0) {
            isAlive = false;
            return;
        }

        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);

        if (apple.x == newHead.x && apple.y == newHead.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

    public GameObject createNewHead() {

        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        GameObject newHead;

        if (direction == Direction.LEFT) {
            newHead = new GameObject(headX - 1, headY);
        } else if (direction == Direction.DOWN) {
            newHead = new GameObject(headX, headY + 1);
        } else if (direction == Direction.RIGHT) {
            newHead = new GameObject(headX + 1, headY);
        } else {
            newHead = new GameObject(headX, headY - 1);
        }
        return newHead;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        boolean flag = false;

        //Метод checkCollision(GameObject) должен возвращать true, если координаты объекта,
        // пришедшего параметром, совпали с координатами одного из элементов змеи (список snakeParts).
        for (int i = 0; i < snakeParts.size(); i++) {
            if (snakeParts.get(i).x == gameObject.x && snakeParts.get(i).y == gameObject.y) {
                flag = true;
            }
        }
        return flag;
    }

    public int getLength() {
        return snakeParts.size();
    }
}

