package com.javarush.games.game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {

    private int[][] array = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
    };

    public static void main(String[] args) {


    }

    private boolean canUserMove() {
        //возвращает true, если кол-во нулевых эл-тов больше нуля
        int count = 0;
        boolean flag = false;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == 0) {
                    count++;
                }
            }
        }

        if (count > 0) {
            flag = true;
        }

        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - 1; j++) {
                    if (array[i][j] == array[i][j + 1]) {
                        flag = true;
                    }
                }
            }
            rotateClockwise();
        }

        return flag;
    }

    private int[][] rotateClockwise() {
        int[][] temp = new int[array.length][array.length];
        for (int i = 0; i < temp.length; i++) {
            int y = 3;
            for (int j = 0; j < temp.length; j++) {
                temp[i][j] = array[y][i];
                y -= 1;
            }
        }
        array = temp;

        return array;
    }
}
