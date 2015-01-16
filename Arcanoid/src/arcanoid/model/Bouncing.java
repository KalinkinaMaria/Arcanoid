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
     * @param element элемент
     */
    public void handleCollision (Point position, FieldElement element) {
        
        //с ракеткой
        Point position1 = new Point(this.position().x + (int)this.size().width()/2, element.position().y);
        Point pointMiddleRacket = new Point((element.position().x+(int)element.size().width()/2), element.position().y);
        double halfRacket = element.size().width()/2.0;
        double y;
        double x;
        
        if (pointMiddleRacket.x < position1.x) {
            x = position1.x - pointMiddleRacket.x;
        } else {
            x = pointMiddleRacket.x - position1.x;
        }
        
        y = Math.sqrt(halfRacket*halfRacket - x*x);

        if (this.speed().x() <= 0) {
            x = -x;
        }
        
        this.setSpeed(new SpeedVector(x/300, -y/300));
    }
    
    /**
     * Обработать столкновение
     * 
     * @param axis ось
     * @param position позиция удара 
     */
    protected void handleCollision (Axis axis, Point position) {        
        this.setSpeed(this.speed().reflect(axis));
    }
    
    public Bouncing clone() {
        Bouncing element = new Bouncing(this.table);
        element.copy(this);
        return element;
    }
}
