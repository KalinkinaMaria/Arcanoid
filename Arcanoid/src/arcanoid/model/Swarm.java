/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;
import arcanoid.events.BallFallenListener;
import arcanoid.service.Buffer;
import java.util.ArrayList;

/**
 * Рой
 * 
 * @author Елена
 */
public class Swarm extends FieldElement {
    // Вес одного элемента
    private final int elementWeight = 1;
    /** Критический вес*/
    private int criticalWeight;
    /** Элементы роя*/
    private ArrayList<SwarmElement> elements;
    /** Слушатели события конца игры*/
    private ArrayList<BallFallenListener> gameStateChangeListeners = new ArrayList<>();

    /**
     * Конструктор
     * @param table таблица соответствия спрайтов и элементов
     * @param elementNumber количество элементов
     */
    public Swarm(Buffer table, int elementNumber) {
        super(table);
        for (int i = elementNumber; i >=0; i --) {
            addElement(new SwarmElement(table, elementWeight));
        }
    }
    
    /**
     * Вернуть все элементы роя
     */
    public ArrayList<SwarmElement> elements() {
        return elements;
    }
    
    /**
     * Добавить слушателя события окончания игры
     * 
     * @param listener слушатель
     */
    public void addGameStateChangeListener(BallFallenListener listener) {
        
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
    
    /**
     * Прикрепить мяч к рою
     * @param element мяч
     */
    public void attach(Ball element) {
        
    }
    
    /**
     * Добавить элемент роя
     * @param element элемент роя
     */
    private void addElement(SwarmElement element) {
        elements.add(new SwarmElement(table, elementWeight));
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
        return criticalWeight;
    }
    
    /**
     * Вернуть текущий вес роя
     * @return вес роя
     */
    public double weight() {
        return this.weight;
    }
    
    /**
     * Клонирование элемента
     * @return клонированный элемент
     */
    public Swarm clone() {
        Swarm swarm = new Swarm(this.table, (int)this.weight()/this.elementWeight);
        swarm.criticalWeight = this.criticalWeight;
        for (SwarmElement element: this.elements()) {
            swarm.elements.add(element.clone());
        }
        swarm.gameStateChangeListeners = this.gameStateChangeListeners;
        return swarm;
    }
}
