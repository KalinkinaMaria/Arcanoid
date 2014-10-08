/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import java.util.EventListener;

/**
 *
 * @author Мария
 */
public interface BallFailedListener extends EventListener {
    void fail(BallFailedEvent e);
}
