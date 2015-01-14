/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import arcanoid.model.FieldElement;
import java.util.ArrayList;
import java.util.EventObject;

/**
 *
 * @author Елена
 */
public class AttemptStartedEvent extends EventObject {

    public final ArrayList<FieldElement> pushingObjects;
    public AttemptStartedEvent(Object source, ArrayList<FieldElement> pushing) {
        super(source);
        pushingObjects = pushing;
    }
    
}
