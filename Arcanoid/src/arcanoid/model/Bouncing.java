/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import java.awt.Point;
import arcanoid.service.SpeedVector.Axis;

/**
 * Элемент, который может отскочить
 * 
 * @author Елена
 */
public class Bouncing extends FieldElement{
    
    public Bouncing (Buffer table) {
        super(table);
    }
    
    /**
     * Обработать столкновение
     * 
     * @param position позиция удара
     * @param element элемент
     */
    protected void handleCollision (Point position, FieldElement element) {
        
        //с ракеткой
        Point pointMiddleRacket = new Point((element.position().x+(int)element.size().width())/2, element.position().y);
        double halfRacket = element.size().width()/2.0;
        double y;
        double x;
        
        if (pointMiddleRacket.x > position.x) {
            x = position.x - pointMiddleRacket.x;
        } else {
            x = pointMiddleRacket.x - position.x;
        }
        
        y = Math.sqrt(halfRacket*halfRacket - x*x);

        if (this.speed().x() < 0) {
            x = -x;
        }
        
        this.setSpeed(new SpeedVector(x, -y));
    }
    
    /**
     * Обработать столкновение
     * 
     * @param axis ось
     * @param position позиция удара 
     */
    protected void handleCollision (Axis axis, Point position) {
        int y = this.position().y, x = this.position().x;
        this.setSpeed(this.speed().reflect(axis));
        if (this.position().y < 3) {
            y = 1;
        }
        if (this.position().x < 3) {
            x = 1;
        } else if (this.position().x + this.size().width() > 597) {
            x = 598 - (int)this.size().width();
        }
        
        this.setPosition(new Point(x, y));
    }
    
    public Bouncing clone() {
        Bouncing element = new Bouncing(this.table);
        element.copy(this);
        return element;
    }
}
