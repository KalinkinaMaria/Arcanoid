/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.service;

import arcanoid.model.FieldElement;

/**
 * Импульс силы удара
 * 
 * @author Елена
 */
public class ImpulseOfStrikeForce {
    /**
     * Рассчитать импульс силы удара элемента
     * @param element элемент для которого высчитывается импульс
     * @return  значение импульса
     */
    public static double count(FieldElement element) {
        return element.weight()*element.speed().value();
    }
}
