/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.CollisionHandleEndListener;
import arcanoid.events.GameFieldChangeEvent;
import arcanoid.events.GameFieldChangeEvent.ChangingType;
import arcanoid.events.GameFieldChangeListener;
import arcanoid.service.Buffer;
import arcanoid.service.ImpulseOfStrikeForce;
import arcanoid.service.Size;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Элемент поля
 * 
 * @author Елена
 */
public abstract class FieldElement {
    /** Таблица соответствий элемента поля со спрайтом */
    protected Buffer table;
    /** Вес */
    protected double weight;

    /** Слушатели изменения игрового поля*/
    private ArrayList<GameFieldChangeListener> gameFieldChangeListeners = new ArrayList<>();
    /** Слушатели окончания обработки столкновения ДЛЯ ТЕСТОВ*/
    private ArrayList<CollisionHandleEndListener> collisionHandleEndListeners = new ArrayList<>();
    
    /** 
     * Добавить слушателя создания элемента
     * @param listener слушатель
     */
    public void addGameFieldChangeListener (GameFieldChangeListener listener) {
        gameFieldChangeListeners.add(listener);
    }
    
    /**
     * Испустить сигнал, что элемент поля создан
     */
    private void fireGameFieldChange(boolean creation) {
        GameFieldChangeEvent event;
        if (creation) {
            event = new GameFieldChangeEvent(this, ChangingType.creation, null);
        } else {
            event = new GameFieldChangeEvent(this, ChangingType.removing, null);
        }
        for (GameFieldChangeListener gameListener: gameFieldChangeListeners) {
            gameListener.changeElement(event);
        }
    }
    
    /** 
     * Добавить слушателя окончания обработки ДЛЯ ТЕСТОВ
     * @param listener слушатель
     */
    public void addCollisionHandleEndListener (CollisionHandleEndListener listener) {
        
    }
    
    public ArrayList<SpeedVector> countSpeed(FieldElement element) {
        ArrayList<SpeedVector> result = new ArrayList();
        double speedX1;
        double speedY1;
        double speedX2;
        double speedY2;
        speedX1 = (2*element.weight()*element.speed().x() + (this.weight() - element.weight()) * this.speed().x())/(this.weight() + element.weight());
        speedY1 = (2*element.weight()*element.speed().y() + (this.weight() - element.weight()) * this.speed().y())/(this.weight() + element.weight());
        speedX2 = (2*this.weight()*this.speed().x() + (element.weight() - this.weight())*element.speed().x())/(this.weight() + element.weight());
        speedY2 = (2*this.weight()*this.speed().y() + (element.weight() - this.weight())*element.speed().y())/(this.weight() + element.weight());
        result.add(new SpeedVector(speedX1, speedY1));
        result.add(new SpeedVector(speedX2, speedY2));
        return result;
    }
    
    /**
     * Испустить сигнал, что элемент закончил обработку ДЛЯ ТЕСТОВ
     */
    private void fireCollisionHandleEnd() {
        
    }
    
    /**
     * Конструктор
     * @param table 
     */
    public FieldElement ( Buffer table) {
        this.table = table;

    }
    
    /** 
     * Получить позицию
     * 
     * @return позиция
     */
    public Point position() {
        return new Point((int)table.getSprite(this).getX(), (int)table.getSprite(this).getY());
    }
    
    public void setPosition(Point point) {
        table.getSprite(this).setX(point.getX());
        table.getSprite(this).setY(point.getY());
    }
    
    /**
     * Получить размер
     * 
     * @return размер
     */
    public Size size() {
        return new Size(table.getSprite(this).getHeight(), table.getSprite(this).getWidth());
    }
    
    /**
     * Полуить спрайт, соответствующий данному элементу
     * 
     * @return спрайт
     */
    protected Sprite getSprite () {
        return table.getSprite(this);
    }
    
    /**
     * Получить скорость
     * 
     * @return вектор скорости
     */
    public SpeedVector speed() {
        Sprite sprite = table.getSprite(this);
        sprite.getHorizontalSpeed();
        return new SpeedVector(table.getSprite(this).getHorizontalSpeed(), table.getSprite(this).getVerticalSpeed());
    }
    
    /**
     * Получить вес
     * 
     * @return вес
     */
    public double weight() {
        return weight;
    }
    
    /**
     * Обработать столкновение
     * 
     * @param element элемент
     */
    public void handleCollision(FieldElement element) {
        // Вызывает в зависимости от типа 1 из 3 методов
        // Сюда передается копия элмента до столкновения
        if (element instanceof Bounced) {
            //кирпич, мяч, ракетка
        } else {
            this.handelCollision(ImpulseOfStrikeForce.count(element));
        }
    }
    
    /**
     * Обработать столкновение при соударение с тем, от которого нельзя отскочить
     * 
     * @param impulse 
     */
    private void handelCollision(double impulse) {
        
    }
    
    /**
     * Задать скорость
     * 
     * @param speed вектор скорости
     */
    public void setSpeed (SpeedVector speed) {
        table.getSprite(this).setSpeed(speed.x(), speed.y());
    }
    
    /**
     * Задать вес
     * 
     * @param weight вес
     */
    public void setWeight (double weight) {
        this.weight = weight;
    }
    
    protected void copy(FieldElement other) {
        other.weight = this.weight;
        other.setSpeed(this.speed());
        other.gameFieldChangeListeners = this.gameFieldChangeListeners;
        other.collisionHandleEndListeners = this.collisionHandleEndListeners;
        other.setPosition(this.position());
    }
    
    public abstract FieldElement clone();
    
    public void setRightPosition(int delta) {
        int y = this.position().y, x = this.position().x;
        if (this.position().y < 3) {
            y = 1;
        }
        if (this.position().x < 3 + delta) {
            x = 1 + delta;
        } else if (this.position().x + this.size().width() > 805-delta +1) {
            x = 806 - delta + 1 - (int)this.size().width();
        }
        
        this.setPosition(new Point(x, y));
    }
}
