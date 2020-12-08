package com.javarush.games.racer;

import com.javarush.engine.cell.*;

/**
 * 1. Создал класс RacerGame, наследовался от класса Game,
 *    Создал 2 переменные WIDTH и HEIGHT, сразу их инициализировал
 *    Переопределил метод initialize()
 *    Вызвал в нем метод showGrid(boolean), чтобы убрать отображение сетки
 *    С помощью метода setScreenSize(), задал размер игрового поля
 * 2. По центру дороги будет расположена разделительная полоса.
 *    Создал переменную CENTER_X в ней будет хранится значение координаты полосы по оси x = равно половине ширины игрового поля
 *    (разделительная полоса)
 *    Создал переменную ROADSIDE_WIDTH - обочина. Ее ширина будет равна 14.
 *    Создал createGame(), который будет отвечать за старт новой игры,
 *    вызвал его в методе initialize()d
 *    Создал drawScene(), который будет отвечать за отрисовку всех игровых объектов,
 *    вызвал его в методе createGame()
 *    Создал drawField(), который будет отвечать за отрисовку фона игрового поля,
 *    вызвал его в методе drawScene()
 * 3. Приступим к отрисовке трассы,
 *    Реализовал метод drawField(). Написал условия для отрисовки дороги, разделительной полосы и обочины
 * 4. Переопределил метод setCellColor(),
 *    Задал условие, если параметр метода x или y находится за пределами поля, метод не должен ничего делать.
 * 5. Создал класс GameObject, который будет представлять игровые объекты
 *    Создал переменные x, y, которые будут овечать за координаты верхнего левого угла объекта на игровом поле
 *    Создал матрицу, которая будет овечать за отображение игрового объекта
 *    Создал переменные width, height - высота и ширина матрицы
 *    Создал конструктор, инициализировал в нем переменные
 *    Создал метод draw() для отрисовки объектов, реализовал его
 * 6. Получил класс RoadMarking, он отвечает за дорожную разметку.
 *    Получил класс ShapeMatrix, он хранит числовые матрицы изображения игровых объектов.
 *    Числа в матрице означают порядковый номер цвета в enum Color.
 *    Благодаря числовой матрице изображения, объект будет знать, в какой цвет раскрасить каждую координату.
 *    Создал поле RoadMarking roadMarking, инициализировал его в методе createGame()
 *    В методе drawScene() у объекта roadMarking вызвал метод draw()
 * 7. Создал класс PlayerCar. В нем
 *    создал поле playerCarHeight, инициализировал его.
 *    создал пустой конструктор, в котором вызвал конструктор базового класса с параметрами
 *    В классе RacerGame создал поле PlayerCar player проинициализировал его в методе createGame()
 *    В методе drawScene() у объекта player вызвал метод draw()
 * 8. В классе PlayerCar создал переменную speed, отвечающую за скорость. Инициализировал ее 1
 *    В классе RacerGame создал метод moveAll(), который будет перемещать все подвижные игровые объекты
 *    В методе moveAll вызывал метод move у объекта roadMarking и передал в качестве параметра скорость машины
 *    Переопределил метод onTurn(), в нем вызываем методы moveAll() и drawScene()
 *    В методе createGame() вызвал метод setTurnTimer(int) класса с параметром 40
 * 9.
 **/

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;

    private RoadMarking roadMarking;
    private PlayerCar player;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        // для старта новой игры
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        drawScene();
        // включаем таймер
        // каждые 40 мс движок вызывает метод onTurn
        setTurnTimer(40);
    }

    private void drawScene() {
        // для отрисовки всех игровых объектов
        drawField();
        roadMarking.draw(this);
        player.draw(this);
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT)
            super.setCellColor(x, y, color);
    }


    private void drawField() {
        // для отрисовки фона игрового поля
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                // разделительная полоса
                if (x == CENTER_X) {
                    setCellColor(x, y, Color.WHITE);
                }
                // дорога
                else if (x >= ROADSIDE_WIDTH && x < (WIDTH - ROADSIDE_WIDTH)) {
                    setCellColor(x, y, Color.DIMGREY);
                } else {
                    // обочина
                    setCellColor(x, y, Color.GREEN);
                }
            }
        }
    }

    @Override
    public void onTurn(int step) {
        // https://javarush.ru/groups/posts/2056-razdel-igrih-na-javarush-chastjh-3-obrabotka-sobihtiy
        moveAll();
        drawScene();

    }

    private void moveAll() {
        roadMarking.move(player.speed);

    }
}

