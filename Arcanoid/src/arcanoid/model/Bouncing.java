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
    
    public void handleManagableCollision(FieldElement element) {
        //с ракеткой
        Point position1 = new Point(this.position().x + (int)this.size().width()/2, element.position().y);
        Point pointMiddleRacket = new Point((element.position().x+(int)element.size().width()/2), element.position().y);
        double halfRacket = element.size().width()/2.0;
        double y = 0.0;
        double x = 0.0;
        
        //Проверка на столкновение с углом ракетки
        if (position1.x > pointMiddleRacket.x + halfRacket ||
                position1.x < pointMiddleRacket.x - halfRacket) {
            this.handleCollision (Axis.Z, null);
        } else {                
            x = - (pointMiddleRacket.x - position1.x);

            y = Math.sqrt(halfRacket*halfRacket - x*x);

            if (this.speed().x() == 0 && position1.x < pointMiddleRacket.x) {
                x = -x;
            }
            
            this.setSpeed(new SpeedVector(x/(2*(element.size().width()-this.size().height())), 
                    -y/(2*(element.size().width()-this.size().height()))));
        }
    }
    
    /**
     * Обработать столкновение
     * 
     * @param element элемент
     */
    public void handleCollision (int side, FieldElement element) {
        
        System.out.println(element.speed().equals(new SpeedVector()));
        // Элемент не движется
        if (element.speed().equals(new SpeedVector())) {
            System.out.println("wwwwwwwwwwww");
        } else {
            System.out.println("rrrrrrrrrrrrrr");
            System.out.println(this.speed().x());
            System.out.println(element.speed().x());
            this.setSpeed(this.countSpeed(element).get(0));
        }
        
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
