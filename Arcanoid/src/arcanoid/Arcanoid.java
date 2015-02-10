/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.AttemptEvent;
import arcanoid.events.AttemptListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import arcanoid.view.GameFieldView;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Главный класс игры
 * 
 * @author Мария
 */
public class Arcanoid  extends Game implements AttemptListener {
    /** Игровое поле GTGE*/
    private PlayField playfield;
    /** Фон*/
    private Background background;
    /** Модель игры*/
    private GameModel gameModel;
    /** Предыдущая позиция мыши(для движения)*/
    private int oldMousePosition;
    /** Обстановка, отвечающая за графичечкое отображение элементов*/
    private GameFieldView gameFieldView;
    /** Шрифт для вывода сообщений*/
    private GameFont font;
    /** Сообщение для пользователя*/
    private String message;
    
    // Раскомментировать, что убрать лишние счетчики и надписи
    { distribute = true; }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Arcanoid(), new Dimension(1000,600), false);
        game.start();
    }

    /**
     * Инициализация ресурсов игры
     */
    @Override
    public void initResources() {
        // Создать буффер для связи логики и отображения.
        Buffer buffer = new Buffer();
        // Инициализация полей
        playfield = new PlayField();
        message = "";
        gameFieldView = new GameFieldView(buffer);
        gameModel = new GameModel(buffer);
        // Связать модель и обстановку с помощью сигналов для общения в дальнейшем
        gameModel.createConnectionWithField(gameFieldView);
        gameModel.startGame();
        oldMousePosition  = this.getMouseX();
        // Задать фон
        background = new ImageBackground(getImage("img/1.jpg"), 1000, 600);
        playfield.setBackground(background);
        gameModel.addAttemptListener(this);
        // Зарегистрировать существующие группы спрайтов
        gameFieldView.registerSpriteGroups(playfield);
        hideCursor();
        // Установить правила столкновений игры
        gameModel.registerCollisionRules(gameFieldView);
        gameFieldView.setCollisionBounds(playfield);
        // Установить связь с помощью сигналов с элементом, который отвечает за провал игры
        gameFieldView.setConnectionWithGhangingGameStateElement(gameModel);
        // Получение шрифта из картинки
        String fontString = "ABCDEFGHIJKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz1234567890aaaaaaaaa?!a.:- ";
        font = fontManager.getFont(getImages("img/font.png", 26, 3), fontString);
    }

    /**
     * Обновить игру
     * 
     * @param l время обновления
     */
    @Override
    public void update(long l) {
        // Обновить поле
        playfield.update(l);
        // Запуск мячика
        if (click() && !gameModel.isAttemptStarted()) {
            // Начать попытку
            gameModel.startAttempt();           
            gameFieldView.setAttemptCollisionBounds(playfield);
            // Установить коллизии между объектами
            gameFieldView.setCollisionObjects(playfield);
        }
        // Проверить движение мышки.
        checkMouseMoving(l);
    }

    /**
     * Отрисовка
     * @param gd графика
     */
    @Override
    public void render(Graphics2D gd) {
        String text = "";
        // Отрисовать поле
        playfield.render(gd);
        // Получить данные игры(жизни, очки)
        HashMap<String, String> gameData = gameModel.getGameData();
        // Создать текст по полученным данным
        for (Map.Entry<String, String> entrySet : gameData.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            text += key + ": " + value + " ";
        }
        // Отрисовать текст
        font.drawText(gd, text, GameFont.LEFT, 840, 30, 150, 10, 0);
        if (!message.isEmpty()) {
            font.drawString(gd, message, 370, 300);
        }
    }
    
    /**
     * Проверить движение мыши
     * 
     * @param l время на момент проверки
     */
    public void checkMouseMoving(long l) {
        // Игрок сделал движение мышкой.
        if (this.getMouseX() != oldMousePosition && l != 0) {
            // Обработать действия игрока
            gameModel.processPlayerAction(getMouseSpeed(l));
            oldMousePosition = this.getMouseX();
        }
    }
    
    /**
     * Получить скорость движения мыши
     * @param l время движения
     * @return вектор скорости
     */
    private SpeedVector getMouseSpeed(long l) {
        SpeedVector result;
        result = new SpeedVector(2*(this.getMouseX() - oldMousePosition)/l, 0);
        return result;
    }

    @Override
    public void startMoving(AttemptEvent e) {
        
    }

    /**
     * Вернуться на стратовую позицию
     * 
     * @param e событие окончания попытки
     */
    @Override
    public void returnToStartAttempt(AttemptEvent e) {
        CollisionManager[] collisionGroups = playfield.getCollisionGroups();
        // Удалить старые коллизионные группы
        for (CollisionManager manager: collisionGroups) {
            playfield.removeCollisionGroup(manager);
        }
        // Настроить новые
        gameFieldView.setCollisionBounds(playfield);
        gameFieldView.setCollisionObjects(playfield);
    }
    
    /**
     * Закончить игру
     * 
     * @param success результат игры
     */
    @Override
    public void endGame(boolean success) {        
        if (success) {
            message = "You win!!!";
        } else {
            message = "You lose!!!";
        }
    }
}
