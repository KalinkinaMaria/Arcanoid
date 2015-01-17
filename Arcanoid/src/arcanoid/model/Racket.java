/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.AttemptStartedEvent;
import arcanoid.events.AttemptStartedListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.Sprite;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Ракетка
 * 
 * @author Елена
 */
public class Racket extends Managable implements Bounced, AttemptStartedListener {
    private double speedVectorLength = 0.3;
    public ArrayList<SpeedVector> initialSpeed;
    public Racket(Buffer table) {
        super(table);
        initialSpeed = new ArrayList<>();
    }
    /**
     * Толкнуть мяч
     * 
     * @param ball мяч
     */
    public void pushBall(Ball ball, SpeedVector speed) {
        
        ball.setSpeed(speed);
    }
    
    public Racket clone() {
        Racket racket = new Racket(this.table);
        Sprite sprite = table.getSprite(this);
        sprite = new Sprite(sprite.getImage(), sprite.getX(), sprite.getY());
        table.addPair(racket, sprite);
        racket.copy(this);
        return racket;
    }

    @Override
    public void startMoving(AttemptStartedEvent e) {
        int count = e.pushingObjects.size();
        if (count != 1) {
        double angleStep = 160/(count-1);
        double speedX;
        double speedY;
        for (FieldElement element:e.pushingObjects) {
            speedX = speedVectorLength*Math.cos(10+angleStep*e.pushingObjects.indexOf(element));
            speedY = speedVectorLength*Math.sin(10+angleStep*e.pushingObjects.indexOf(element));
            pushBall((Ball)element, new SpeedVector(speedX, -Math.abs(speedY)));
            SpeedVector speed = element.speed();
        }
        } else {
            pushBall((Ball)e.pushingObjects.get(0), new SpeedVector(0, -speedVectorLength));
        }
    }

    @Override
    public void returnToStartPosition(AttemptStartedEvent e) {
        FieldElement element = e.pushingObjects.get(0);
        element.setPosition(new Point((int)(this.position().x + this.size().width()/2 - element.size().width()/2), (int)(this.position().y - element.size().height())));
    }

    @Override
    public void endGame(boolean success) {
        
    }
}
