/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 *
 * @author Елена
 */
public interface AttemptStartedListener extends EventListener {
    public void startMoving(AttemptStartedEvent e);
    public void returnToStartPosition(AttemptStartedEvent e);
    public void endGame(boolean success);
}
