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
    
    /**
     * Конструктор
     * @param table буффер
     */
    public Bouncing (Buffer table) {
        super(table);
    }
    
    /**
     * Обработать столкновение с управляемым пользователем элементом
     * 
     * @param element элемент, управляемый пользователем
     */
    @Override
    public void handleManagableCollision(FieldElement element) {
        //с управляемым элементом
        Point positionCollision = new Point(this.position().x + (int)this.size().width()/2, this.position().y + (int)this.size().height());
        Point positionMiddleRacket = new Point((element.position().x+(int)element.size().width()/2), element.position().y);
        double lengthHalfRacket = element.size().width()/2.0;

        //Проверка на столкновение с углом элемента        
        boolean collisionRightConer = positionCollision.x > positionMiddleRacket.x + lengthHalfRacket &&
                positionMiddleRacket.y < element.position().y + this.size().height()/2;
        boolean collisionLeftConer = positionCollision.x < positionMiddleRacket.x - lengthHalfRacket &&
                positionMiddleRacket.y < element.position().y + this.size().height()/2;
        // Мячик летит к ракетке к углу элемента
        if (collisionRightConer && this.speed().x() < 0 || 
                collisionLeftConer && this.speed().x() > 0) {
            this.handleCollision (Axis.Z, null);
        // Мячик летит вдоль ракетки к углу элемента
        } else if (collisionRightConer && this.speed().x() > 0 || 
                collisionLeftConer && this.speed().x() < 0) { 
            this.handleCollision (Axis.Y, null);
        // Мячик летит на угол элемента под прямым углом
        } else if (collisionRightConer && this.speed().x() == 0) {
            this.setSpeed(new SpeedVector(0.3, -0.3));
        } else if (collisionLeftConer && this.speed().x() == 0) {
            this.setSpeed(new SpeedVector(-0.3, -0.3));
        // Мячик попадает на поверхность элемента
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
     * Обработать столкновение с тем, от которого можно осткочить
     * 
     * @param element элемент
     */
    public void handleCollision (int side, FieldElement element) {
        // Элемент не движется
        if (element.speed().equals(new SpeedVector())) {
        } else {
            // Элемент движется
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
    
    /**
     * Клонировать
     * @return клон объекта
     */
    public Bouncing clone() {
        Bouncing element = new Bouncing(this.table);
        Sprite sprite = table.getSprite(this);
        sprite = new Sprite(sprite.getImage(), sprite.getX(), sprite.getY());
        table.addPair(element, sprite);
        element.copy(this);
        return element;
    }
}
