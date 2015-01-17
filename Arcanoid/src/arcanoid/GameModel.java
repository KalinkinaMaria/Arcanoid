/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid;

import arcanoid.events.AttemptEvent;
import arcanoid.events.AttemptListener;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.events.GameStateChangeEvent;
import arcanoid.events.GameStateChangeListener;
import arcanoid.model.FieldElement;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import arcanoid.view.Ambiance;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс логической модели игры
 * 
 * @author Елена
 */
public class GameModel implements GameStateChangeListener {
    /** Игровое поле*/
    private GameField field;
    /** Игрок*/
    private Player player;
    /** Флаг, о том, что попытка началась, т.е. игрок запустил шарик*/
    private boolean attemptWasStarted;
    /** Слушатели начала/конца попытки. Элементы, которые запускаются при начале попытки*/
    private ArrayList<AttemptListener> movingElements;
    
    /**
     * Начать игру
     */
    public void startGame() {
        field.createInitialAmbiance(this);
    }
    
    /**
     * Конструктор
     * 
     * @param buffer буффер с элементами
     */
    public GameModel(Buffer buffer) {
        field = new GameField(buffer);
        movingElements = new ArrayList<>();
        attemptWasStarted = false;
        player = new Player(3);
    }
    
    /**
     * Создать связь с помощтю сигналов с полем, чтобы в дальнейшем знать об удалении/добавлении элементов
     * 
     * @param object объект, желающий получать сигналы
     */
    public void createConnectionWithField(GameFieldChangeListener object) {
        field.addGameFieldChangeListener(object);
    }
    
    /** 
     * Добавить слушателя начала попытки
     * 
     * @param listener слушатель
     */
    public void addAttemptStartedListener (AttemptListener listener) {
        movingElements.add(listener);
    }
    
    /**
     * Испустить сигнал, что попытка начата
     */
    private void fireAttemptStarted() {
        AttemptEvent event;
        event = new AttemptEvent(this, field.getElements("arcanoid.model.Ball"));
        for (AttemptListener listener: movingElements) {
            listener.startMoving(event);
        }
    }
    
    /**
     * Испустить сигнал, что попытка закончена
     * 
     * @param element элемент, из-за которого попытка была завершена
     */
    private void fireAttemptEnded(FieldElement element) {
        AttemptEvent event;
        ArrayList<FieldElement> list = new ArrayList<>();
        element.setSpeed(new SpeedVector());
        list.add(element);
        event = new AttemptEvent(this, list);
        for (AttemptListener listener: movingElements) {
            listener.returnToStartPosition(event);
        }
    }
    
    /**
     * Испустить сигнал, что попытка закончена
     * 
     * @param success успех попытки
     */
    private void fireAttemptEnded(boolean success) {
        for (AttemptListener listener: movingElements) {
            listener.endGame(success);
        }
    }
    
    /**
     * Закончить игру
     * 
     * @param success успех/неуспех игры
     */
    public void endGame(boolean success) {
        fireAttemptEnded(success);
    }

    /**
     * Установить флаг, сто попытка начата
     */
    public void attemptWasStarted() {
        attemptWasStarted = true;
    }
    
    /**
     * Установить флаг, что попытка завершена
     */
    public void attemptWasEnded() {
        attemptWasStarted = false;
    }
    
    /**
     * Поптыка начата?
     * 
     * @return флаг, идет ли попытка или нет
     */
    public boolean isAttemptStarted() {
        return attemptWasStarted;
    }
    
    /**
     * Начать попытку
     */
    public void startAttempt() {
        field.changePositionForAttempt();
        fireAttemptStarted();
        attemptWasStarted = true;
    }
    
    @Override
    /**
     * Провал попытки
     * 
     * @param e событие изменения состояния игры
     */
    public void fail(GameStateChangeEvent e) {
        // Посчитать жизни игрока
        int lives = player.lives() - 1;
        ArrayList<FieldElement> elements = field.getElements("arcanoid.model.Ball");
        // Нет больше элементов, которые управляют ходом игры
        if (elements.size() == 1) {
            // Есть еще жизни
            if (lives != 0) {
                player.setLives(lives);
                // Вернуть шарик на место.
                fireAttemptEnded((FieldElement) e.element);
                attemptWasStarted = false;
            } else {
                // Закончить игру
                endGame(false);
            }
        } else {
            // Удалить элемент с поля
            field.removeElement((FieldElement) e.element);
        }
    }
    
    /**
     * Получить данные игры
     * 
     * @return карта с данными игры
     */
    public HashMap<String, String> getGameData() {
        HashMap<String, String> result = new HashMap <String, String>();
        result.put("Lives", String.valueOf(player.lives()));
        result.put("Score", String.valueOf(player.score()));
        return result;
    }

    @Override
    /**
     * Закончить игру
     * 
     * @param e событие изменения состояния игры
     */
    public void endGame(GameStateChangeEvent e) {
        // Т.к. пока нет роя, не реализовано
    }
    
    /**
     * Обработать действия пользователя
     * 
     * @param speed скорость движения мышью
     */
    public void processPlayerAction(SpeedVector speed) {
        FieldElement racket = field.getElement("arcanoid.model.Racket");
        if (racket != null) {
            racket.setSpeed(speed);
        }
        // Если попытка не начата, двигать все объекты на ракетке вместе с ней
        if (!isAttemptStarted()) {
            for (FieldElement element: field.getElements("arcanoid.model.Ball")) {
                element.setSpeed(speed);
            }
        }
    }
    
    /**
     * Задать правила столкновения в игре
     * 
     * @param ambiance обстановка
     */
    public void registerCollisionRules(Ambiance ambiance) {
        ambiance.addCollidedGroupPair("Racket", "Ball");
        ambiance.addCollidedGroupPair("Ball", "Ball");
    }
}
