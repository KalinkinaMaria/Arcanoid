/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.service;

/**
 * Размер
 * 
 * @author Елена
 */
public class Size {
    
    /** Высота*/
    private double height;
    /** Ширина*/
    private double width;
    
    /** 
     * Конструктор
     */
    public Size() {
        height = 0;
        width = 0;
    }
    
    /** 
     * Высота
     * @return высота
     */
    public double height() {
        return this.height;
    }
    
    /** 
     * Ширина
     * @return ширина
     */
    public double width() {
        return this.width;
    }
    
    /**
     * Конструктор
     * @param height высота
     * @param width ширина
     */
    public Size(double height, double width) {
        this.height = height;
        this.width = width;
    }
}
