/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.service;

/**
 *
 * @author Елена
 */
public class SpeedVector {
    /**
    * Перечисление осей
    * @author Елена
    */
    public enum Axis {
        X,
        Y,
        Z;
    }
    
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
     * Получить горизонтальную составляющую веткора скорости
     * @return 
     */
    public double x() {
        return speedX;
    }
    
    /**
     * Получить вертикальную составляющую веткора скорости
     * @return 
     */
    public double y() {
        return speedY;
    }
    
    /**
     * Вычислить сумму векторов
     * 
     * @param other другой вектор скорости
     * @return результирующий вектор скорости
     */
    public SpeedVector sum (SpeedVector other) {
        return new SpeedVector(this.x() + other.x(), this.y() + other.y());
    }
    
    /**
     * Отразить вектор
     * 
     * @param axis ось, относительно которой будет происходить отражение
     * @return результирующий вектор
     */
    public SpeedVector reflect (Axis axis) {
        SpeedVector result = this.clone();
        switch (axis) {
            case X: 
                result.speedY = -this.y();
                break;
            case Y:
                result.speedX = -this.x();
                break;
            case Z:
                result.speedX = -this.speedX;
                result.speedY = -this.speedY;
                break;
        }
        return result;
    }
    
    /**
     * Умножить вектор скорости на константу
     * @param constant константа
     * @return результирующий вектор
     */
    public SpeedVector multiplication (double constant) {
        return new SpeedVector(constant*this.x(), constant*this.y());
    }
    
    /**
     * Сравнение
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        boolean result = getClass() == other.getClass();
        
        if (Math.abs(this.speedX - ((SpeedVector)other).speedX) <= 0.01 &&
                Math.abs(this.speedY - ((SpeedVector)other).speedY) <= 0.01) {
            result = result && true;
        }
        
        return (result);
    }
    
    public SpeedVector clone() {
        return new SpeedVector(this.x(), this.y());
    }
    
    public double value() {
        return Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
    }
}
