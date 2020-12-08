package com.javarush.games.snake;

/**
 * 1.  Создал класс SnakeGame,
 *     унаслеовался от класса Game.
 *     Создал 2 переменные WIDTH и HEIGHT, сразу проинициализировал их.
 *     Переопределил метод initialize(), в нем будут содержаться команды, которые выполнятся один раз при запуске игры.
 *     Сразу задал размеры ширины и высоты игрового поля
 * 2.  Создал метод drawScene(),
 *     который будет отвечать за отрисовку экрана. Раскрасил экран в цвет DARKOLIVEGREEN
 *     Создал метод createGame(), который будет отвечать за действия, которые нужно выполнить для создания игры,
 *     в нем вызвал метод drawScene()
 * 3.  Создал дополнительный класс GameObject, где будем хранить объекты игрового поля. Создал 2 переменные и конструктор
 * 4.  Создал класс Apple.
 *     Определил в нем переменную APPLE_SIGN, где будет хранится наше яблоко.
 *     Так как класс наследник класса GameObject, определил в нем конструктор с ключевым словом this
 *     Создал метод draw(Game game) с объектом game в параметрах. Сразу импортировал этот класс
 *     В методе draw() вызвал метод setCellValueEx(с отрисовкой нашего яблока)
 * 5.  Создал класс Snake.
 *     В класса создал поле snakeParts класса ArrayList.
 *     В конструкторе создал 3 объекта класса GameObject и заполнил ими список snakeParts
 * 6.  Отрисовываем змейку.
 *     В классе Snake создал 2 поля HEAD_SIGN и BODY_SIGN, сразу проинициализировал их
 *     Создал метод draw(Game game).
 *     В нем вызвал метод setCellValue() для каждого объекта GameObject (элемента списка snakeParts)
 *     В классе SnakeGame создал поле snake класса Snake
 *     В методе createGame создал объект класса Snake
 *     В методе drawScene() у объекта snake вызвал метод draw с параметром this
 * 7.  В классах Apple и Snake создал переменные boolean isAlive, которые будут отображать состояния, "жив" или нет
 *     Создал enum Direction со значениями UP, RIGHT, DOWN, LEFT, чтобы указать направления змейки
 *     В методе draw(Game) класса Snake изменил метод setCellValue(int, int, String) на вызовы метода
 *     setCellValueEx(int, int, Color, String, Color, int). Создал отдельную переменную Color color, где с помощью тернарного оператора
 *     определил цвет. Если змея жива - зеленый, нет - цвет красный
 *     В классе Snake создал поле Direction direction, инициализировал его значением Direction.LEFT и сделал сеттер для него
 * 8.  В классе Snake создал метод move(), который будет определять логику передвижения змейки
 *     В классе SnakeGame переопределил родительский метод onTurn(int), в нем будем описывать все,
 *     что должно происходить в игре на протяжении одного хода
 *     В классе SnakeGame создал переменную turnDelay типа int, она будет отвечать за продролжительность хода.
 *     В методе createGame() инициализировал ее значением 300мс/ход
 * 9.  В классе Snake реализовал метод createNewHead(), который создает новый элемент GameObject и возвращает его.
 *     В классе Snake создал и реализовал метод removeTail(). Он удаляет последний элемент из списка snakeParts
 * 10. Реализовал метод метод move(). Сделал проверку на выход змейки за пределы игрового поля.
 *     Если новая голова вышла за пределы игрового поля, установил состояние змейки в "неживая".
 *     Заменил метод setCellColor() в классе SnakeGame на setCellValueEx, который теперь будет очищать игровое поле от ненужных элементов.
 *     Для этого в методе drawScene() нужно не только изменять цвет ячеек, но и устанавливать в их значении пустую строку.
 * 11. Переопределил метод onKeyPress.
 *     В классе Snake, в методе setDirection сделал проверку, чтобы змейка не меняла направление движения,
 *     если параметр метода противоположен текущему направлению.
 * 12. Добавил переменную apple в класс SnakeGame. В методе createGame() инициализировал ее
 *     В методе drawScene() "нарисовал" яблоко (apple.draw(this);)
 *     Переписал метод move() в классе Snake. (теперь это move(Apple apple)).
 * 13. В классе SnakeGame создал метод void createNewApple(). Метод будет генерировать случайные координаты ячейки
 *     в пределах игрового поля, на которой будет появляться яблоко
 *     * исправил метод createGame()
 *     * исправил метод onTurn(), сделал проверку: если apple.isAlive == false, необходимо вызвать метод createNewApple().
 * 14. В классе Snake создал метод checkCollision(GameObject), который будет проверять новосозданную голову змейки
 *     на совпадение со всеми остальными элементами её тела.
 *     В метод move(Apple) добавил проверку на столкновения новой головы и тела. if (checkCollision(newHead)) {isAlive = false;}
 * 15. В классе SnakeGame создал переменную isGameStopped, которая будет хранить состояние игры.
 *     В методе createGame() проинициализировал ее.
 *     Создал и реализовал метод gameOver(). Когда игра проиграна, её нужно остановить и вывести сообщение об этом игроку.
 *     Добавил в метод onTurn(int) проверку (isAlive == false), вызываем метод gameOver
 * 16. Создал константу GOAL и инициализировал её значением 28, где будем хранить максимальный размер змейки.
 *     В классе Snake создал метод int getLength(), который возвращает количество сегментов змеи в списке snakeParts.
 *     В классе SnakeGame создал и реализовал метод void win().
 *     В методе onTurn(int) вызвал метод win().
 * 17. Устраняем баг (яблоко может сгенерироваться на теле змейки).
 *     Для его устранения, в методе createNewApple() сделал проверку (до тех пора пока координаты яблока и змеи совпадают,
 *     мы продолжаем создавать яблоко с новыми координатами.
 *     Реализовал рестарт игры в методе onKeyPress.
 *     Если параметр метода — клавиша SPACE, и игра была остановлена (isGameStopped == true) - создаем новую игру.
 * 18. Поправил управления.
 *     Значения поля direction могут меняться только на валидные в данный момент: LEFT, RIGHT, UP, DOWN.
 *     direction == Direction.LEFT && this.direction != Direction.RIGHT && snakeParts.get(0).y != snakeParts.get(1).y
 * 19. Создал переменную score, которая будет хранить все счет набранных очков.
 *     В методе инициалириовал createGame() ее нулем.
 *     В методе onTurn() написал условие, что увеличиваем переменную score на 5 после каждого съеденного яблока и
 *     уменьшаю продолжительность хода (перемнная turnDelay) - скорость змейки
 * 20. Наслаждаемся)
 * */

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private Apple apple;

    private boolean isGameStopped;
    private int turnDelay;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        //будет отвечать за действия, которые нужно выполнить для создания игры
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        isGameStopped = false;
        setScore(score = 0);
        //apple = new Apple(5, 5);
        createNewApple();
        drawScene();
        setTurnTimer(turnDelay = 300);
    }

    private void drawScene() {
        //будет отвечать за отрисовку экрана.
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DIMGREY, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        // в нем будем описывать все,
        // что должно происходить в игре на протяжении одного хода
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
            setScore(score += 5);
            setTurnTimer(turnDelay -= 10);
        }
        if (!snake.isAlive)
            gameOver();
        if (snake.getLength() > GOAL)
            win();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        //Проверь, что в методе onKeyPress(Key) не вызывается метод createGame(), если isGameStopped == false.
        switch (key) {
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                if (isGameStopped)
                    createGame();
        }
    }

    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        while (snake.checkCollision(apple)) {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.HOTPINK, "GAME OVER", Color.BLACK, 75);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.HOTPINK, "YOU WIN!", Color.BLACK, 75);
    }
}
