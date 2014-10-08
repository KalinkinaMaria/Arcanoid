/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.buffer.Buffer;
import arcanoid.events.GameEndedListener;
import arcanoid.service.ImpulseOfStrikeForce;
import arcanoid.service.SpeedVector;
import java.util.ArrayList;

/**
 * Рой
 * 
 * @author Елена
 */
public class Swarm extends FieldElement implements Unbounced {
    /** Критический вес*/
    private int criticalWeight;
    /** Элементы роя*/
    private ArrayList<SwarmElement> elements;
    /** Слушатели события конца игры*/
    private ArrayList<GameEndedListener> gameEndListeners = new ArrayList<>();

    public Swarm(double weight, SpeedVector speed, int subtype, Buffer table) {
        super(weight, speed, subtype, table);
    }
    
    /**
     * Добавить слушателя события окончания игры
     * 
     * @param listener слушатель
     */
    public void addGameEndedListener(GameEndedListener listener) {
        
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
    private void createSwarmElement(FieldElement element) {
        
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
}
