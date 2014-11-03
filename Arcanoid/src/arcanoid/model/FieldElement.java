/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.buffer.Buffer;
import arcanoid.events.GameFieldChangeListener;
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
    /** Скорость */
    protected SpeedVector speed;
    /** Слушатели изменения игрового поля*/
    private ArrayList<GameFieldChangeListener> gameFieldChangeListeners = new ArrayList<>();
    
    /** 
     * Добавить слушателя создания элемента
     * @param listener слушатель
     */
    public void addGameFieldChangeListener (GameFieldChangeListener listener) {
        
    }
    
    /**
     * Испустить сигнал, что элемент поля создан
     */
    private void fireGameFieldChange() {
        
    }
    
    /**
     * Конструктор
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

    }
    
    /**
     * Получить размер
     * 
     * @return размер
     */
    public Size size() {
        
    }
    
    /**
     * Полуить спрайт, соответствующий данному элементу
     * 
     * @return спрайт
     */
    private Sprite getSprite () {
        
    }
    
    /**
     * Получить скорость
     * 
     * @return вектор скорости
     */
    public SpeedVector speed() {
        return speed;
    }
    
    /**
     * Обработать столкновение
     * 
     * @param element элемент
     */
    public void handleCollision(FieldElement element) {
        // Вызывает в зависимости от типа 1 из 3 методов
        // Сюда передается копия элмента до столкновения
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
        this.speed = speed;
    }
    
    /**
     * Задать вес
     * 
     * @param weight вес
     */
    public void setWeight (double weight) {
        this.weight = weight;
    }
}
