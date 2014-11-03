/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.service;

/**
 * Перечисление осей
 * @author Елена
 */
enum Axis {
    X,
    Y,
    Z;
}

/**
 *
 * @author Елена
 */
public class SpeedVector {
    
    /** Скорость по X*/
    private double speedX;
    /** Скорость по Y*/
    private double speedY;
    
    /**
     * Конструктор
     */
    public SpeedVector() {
        speedX = 0;
        speedY = 0;
    }
    
    /** 
     * Конструктор
     * @param speedX скорость по X
     * @param speedY скорость по Y
     */
    public SpeedVector(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }
    
    /**
     * Вычислить сумму векторов
     * 
     * @param other другой вектор скорости
     * @return результирующий вектор скорости
     */
    public SpeedVector sum (SpeedVector other) {
        
    }
    
    /**
     * Отразить вектор
     * 
     * @param axis ось, относительно которой будет происходить отражение
     * @return результирующий вектор
     */
    public SpeedVector reflect (Axis axis) {
        
    }
    
    /**
     * Умножить вектор скорости на константу
     * @param constant константа
     * @return результирующий вектор
     */
    public SpeedVector multiplication (double constant) {
        
    }
    
    /**
     * Сравнение
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return (getClass() == other.getClass() && this.speedX == ((SpeedVector)other).speedX && this.speedY == ((SpeedVector)other).speedY);
    }
}
