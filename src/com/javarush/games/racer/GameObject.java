package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject {
    // координаты верхнего левого угла объекта на игровом поле;
    public int x;
    public int y;
    // матрица отображения игрового объекта
    public int[][] matrix;
    // высота и ширина матрицы
    public int width;
    public int height;

    public GameObject(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        width = matrix[0].length;
        height = matrix.length;
    }

    public void draw(Game game) {
        // для отрисовки объектов
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                // В методе draw(Game) для каждой ячейки матрицы matrix должен быть вызван метод setCellColor(int, int, Color) у объекта типа Game.
                // В качестве параметров необходимо передать: x класса + x в матрице, y класса + y в матрице, цвет
                // Чтобы получить цвет, нужно использовать Color.values()[matrix[i][j]], где:
                //i — координата y в матрице matrix,
                //j — координата x в матрице matrix.
                game.setCellColor(this.x + y, this.y + x, Color.values()[matrix[x][y]]);
            }
        }
    }
}
