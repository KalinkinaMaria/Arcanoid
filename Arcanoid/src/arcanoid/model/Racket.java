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
public class Racket extends FieldElement implements Bounced, AttemptStartedListener {

    public final SpeedVector initialSpeed = new SpeedVector(0,-0.3);
    public Racket(Buffer table) {
        super(table);
    }
    /**
     * Толкнуть мяч
     * 
     * @param ball мяч
     */
    public void pushBall(Ball ball) {
        ball.setSpeed(initialSpeed);
    }
    
    public Racket clone() {
        Racket racket = new Racket(this.table);
        Sprite sprite = new Sprite();
        table.addPair(racket, sprite);
        racket.copy(this);
        return racket;
    }

    @Override
    public void startMoving(AttemptStartedEvent e) {
        for (FieldElement element:e.pushingObjects) {
            pushBall((Ball)element);
        }
    }

    @Override
    public void returnToStartPosition(AttemptStartedEvent e) {
        FieldElement element = e.pushingObjects.get(0);
        element.setPosition(new Point((int)(this.position().x + this.size().width()/2 - element.size().width()/2), (int)(this.position().y - element.size().height())));
    }
}
