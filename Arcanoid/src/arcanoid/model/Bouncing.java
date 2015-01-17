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
import com.golden.gamedev.object.Sprite;
import java.util.ArrayList;

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
        Point positionCollision = new Point(this.position().x + (int)this.size().width()/2, this.position().y + (int)this.size().height());
        Point positionMiddleRacket = new Point((element.position().x+(int)element.size().width()/2), element.position().y);
        double lengthHalfRacket = element.size().width()/2.0;

        //Проверка на столкновение с углом ракетки        
        boolean collisionRightConer = positionCollision.x > positionMiddleRacket.x + lengthHalfRacket &&
                positionMiddleRacket.y < element.position().y + this.size().height()/2;
        boolean collisionLeftConer = positionCollision.x < positionMiddleRacket.x - lengthHalfRacket &&
                positionMiddleRacket.y < element.position().y + this.size().height()/2;
        // Мячик летит к ракетке к углу ракетки
        if (collisionRightConer && this.speed().x() < 0 || 
                collisionLeftConer && this.speed().x() > 0) {
            this.handleCollision (Axis.Z, null);
        // Мячик летит вдоль ракетки к углу какетки
        } else if (collisionRightConer && this.speed().x() > 0 || 
                collisionLeftConer && this.speed().x() < 0) { 
            this.handleCollision (Axis.Y, null);
        // Мячик летит на угол ракетки под прямым углом
        } else if (collisionRightConer && this.speed().x() == 0) {
            this.setSpeed(new SpeedVector(0.3, -0.3));
        } else if (collisionLeftConer && this.speed().x() == 0) {
            this.setSpeed(new SpeedVector(-0.3, -0.3));
        // Мячик попадает на поверхность ракетки
        } else if (positionCollision.x > element.position().x &&
                positionCollision.x < element.position().x + element.size().width()) {
            double lengthSpeedVector = this.speed().value();
            double distanceToMiddleRacket = positionCollision.x > positionMiddleRacket.x ?
                    positionCollision.x - positionMiddleRacket.x :
                    positionMiddleRacket.x - positionCollision.x;
            double angleNewSpeedVector = Math.acos(distanceToMiddleRacket/lengthHalfRacket);
            double newSpeedVectorX = positionCollision.x > positionMiddleRacket.x ?
                    Math.cos(angleNewSpeedVector)*lengthSpeedVector :
                    - (Math.cos(angleNewSpeedVector)*lengthSpeedVector);
            double newSpeedVectorY = - (Math.sin(angleNewSpeedVector)*lengthSpeedVector);
            SpeedVector newSpeedVector = new SpeedVector(newSpeedVectorX, newSpeedVectorY);
            this.setSpeed(newSpeedVector);
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
        } else {

            ArrayList<SpeedVector> countSpeed = this.countSpeed(element);
            this.setSpeed(countSpeed.get(0));
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
        Sprite sprite = table.getSprite(this);
        sprite = new Sprite(sprite.getImage(), sprite.getX(), sprite.getY());
        table.addPair(element, sprite);
        element.copy(this);
        return element;
    }
}
