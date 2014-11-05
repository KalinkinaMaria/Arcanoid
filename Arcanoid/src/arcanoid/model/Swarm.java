/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.buffer.Buffer;
import arcanoid.events.GetCritycalWeightListener;

import arcanoid.service.ImpulseOfStrikeForce;
import arcanoid.service.SpeedVector;
import java.util.ArrayList;

/**
 * Рой
 * 
 * @author Елена
 */
public class Swarm extends FieldElement {
    /** Критический вес*/
    private int criticalWeight;
    /** Элементы роя*/
    private ArrayList<SwarmElement> elements;
    /** Слушатели события конца игры*/
    private ArrayList<GameStateChangeListener> gameStateChangeListeners = new ArrayList<>();

    public Swarm(Buffer table, int elementNumber) {
        super(table);
        for (int i = elementNumber; i >=0; i --) {
            addElement(new SwarmElement(table));
        }
    }
    
    /**
     * Вернуть все элементы роя
     */
    public ArrayList<SwarmElement> elements() {
        
    }
    
    /**
     * Добавить слушателя события окончания игры
     * 
     * @param listener слушатель
     */
    public void addGameStateChangeListener(GameStateChangeListener listener) {
        
    }
    
    /**
     * Испустить сигнал об окончании игры
     */
    private void fireGameEnded() {
        
    }
    
    /**
     * Создать элемент роя
     * 
     * @param element элемент поля, перерождаемый в элемент роя 
     */
    private void createSwarmElement(Ball element) {
        
    }
    
    public void attach(Ball element) {
        
    }
    
    private void addElement(SwarmElement element) {
        elements.add(new SwarmElement(table));
        this.weight += element.weight();
    }
    
    /**
     * Установить критический вес
     * 
     * @param weight критический вес 
     */
    public void setCriticalWeight (int weight) {
        this.criticalWeight = weight;
    }
    
    /**
     * Получить критический вес
     * 
     * @return критический вес
     */
    public int criticalWeight() {
        
    }
    
    public double weight() {
        return this.weight;
    }
}
