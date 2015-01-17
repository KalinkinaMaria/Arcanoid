/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;
import arcanoid.events.AttemptEvent;
import arcanoid.events.AttemptListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;
import java.awt.Point;

/**
 * Ракетка
 * 
 * @author Елена
 */
public class Racket extends Managable implements Bounced, AttemptListener {
    /** Длина вектора скорости запускаемого мяча*/
    private double speedVectorLength = 0.35;
    
    /**
     * Конструктор
     * @param table таблица соответствия спрайтов и элементов
     */
    public Racket(Buffer table) {
        super(table);
    }
    /**
     * Толкнуть мяч
     * 
     * @param ball мяч
     */
    protected void pushBall(Ball ball, SpeedVector speed) {        
        ball.setSpeed(speed);
    }
    
    /**
     * Клонирование элемента
     * @return клонированный элемент
     */    
    public Racket clone() {
        Racket racket = new Racket(this.table);
        //Клонирование спрайта
        Sprite sprite = table.getSprite(this);
        sprite = new Sprite(sprite.getImage(), sprite.getX(), sprite.getY());
        table.addPair(racket, sprite);
        racket.copy(this);
        return racket;
    }

    /**
     * Реакция на нажание кнопки мыши
     * @param e сигнал
     */
    @Override
    public void startMoving(AttemptEvent e) {
        int count = e.pushingObjects.size();
        // Проверка на наличие больше чем одного мяча на ракетке
        if (count != 1) {
            double angleStep = 160/(count-1);
            double speedX;
            double speedY;
            // Высчитать скорости для каждого мяча на ракетке
            for (FieldElement element:e.pushingObjects) {
                speedX = speedVectorLength*Math.cos(10+angleStep*e.pushingObjects.indexOf(element));
                speedY = speedVectorLength*Math.sin(10+angleStep*e.pushingObjects.indexOf(element));
                pushBall((Ball)element, new SpeedVector(speedX, -Math.abs(speedY)));
                SpeedVector speed = element.speed();
            }
        } else { // Один мяч на ракетке
            pushBall((Ball)e.pushingObjects.get(0), new SpeedVector(0, -speedVectorLength));
        }
    }

    /**
     * Задать начальную позицию
     * @param e сигнал
     */
    @Override
    public void returnToStartPosition(AttemptEvent e) {
        FieldElement element = e.pushingObjects.get(0);
        element.setPosition(new Point((int)(this.position().x + this.size().width()/2 - element.size().width()/2), (int)(this.position().y - element.size().height())));
    }

    /**
     * Окончание игры
     * @param success параметр окончания игры
     */
    @Override
    public void endGame(boolean success) {
        
    }
}
