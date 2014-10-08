/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.buffer.Buffer;
import arcanoid.events.FieldElementCollisionListener;
import arcanoid.events.FieldElementCreatedListener;
import arcanoid.events.FieldElementRemovedListener;
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
    private Buffer table;
    /** Подтип элемента для отличия элементов одного типа, которые могут сталкиваться между собой */
    private int subtype;
    /** Вес */
    private double weight;
    /** Скорость */
    private SpeedVector speed;
    /** Слушатели столкновения элемента */
    private ArrayList<FieldElementCollisionListener> viewListeners = new ArrayList<>();
    /** Слушатели создания объекта*/
    private ArrayList<FieldElementCreatedListener> createListeners = new ArrayList<>();
    /** Слушатели удаления объекта*/
    private ArrayList<FieldElementRemovedListener> removeListeners = new ArrayList<>();
    
    /** 
     * Добавить слушателя создания элемента
     * @param listener слушатель
     */
    public void addFieldElementCreatedListener (FieldElementCreatedListener listener) {
        
    }
    
    /**
     * Испустить сигнал, что элемент поля создан
     */
    private void fireFieldElementCreated() {
        
    }
    
    /** 
     * Добавить слушателя удаления элемента
     * @param listener слушатель
     */
    public void addFieldElementRemovedListener (FieldElementRemovedListener listener) {
        
    }
    
    /**
     * Испустить сигнал, что элемент поля удален
     */
    private void fireFieldElementRemoved() {
        
    }
    
    /**
     * Добавить слушателя столкновения элемента
     * @param viewListener слушатель
     */
    public void addFieldElementCollisionListener(FieldElementCollisionListener viewListener) {
        
    }
    
    /**
     * Испустить событие столкновение элемента
     */
    private void fireFieldElementCollision() {
        
    }
    
    /**
     * Конструктор
     * @param weight вес
     * @param speed скорость
     * @param subtype подтип
     */
    public FieldElement (double weight, SpeedVector speed, int subtype, Buffer table) {
        this.table = table;
        this.speed = speed;
        this.weight = weight;
        this.subtype = subtype;
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
     * @param force импульс силы удара
     * @param element элемент
     */
    public void handleCollision(ImpulseOfStrikeForce force, FieldElement element) {
        
    }
    
    /**
     * Задать скорость
     * 
     * @param speed вектор скорости
     */
    public void setSpeed (SpeedVector speed) {
        this.speed = speed;
    }
}
