/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import com.golden.gamedev.object.collision.CollisionRect;
import java.util.ArrayList;
import java.util.Map;

/**
 * Обработчик столкновений
 * 
 * @author Елена
 */
public class CollisionHandler implements SpritesCollidedListener {
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;

    public CollisionHandler (Buffer table) {
        this.table = table;
    }
    
    /**
     * Определить тип столкновения
     * 
     * @param storage хранилище со столкнувшимися спрайтами
     */
    public void getCollisionType(Map storage) {
        
    }
    
    /**
     * Получить элемент
     * 
     * @param sprite спрайт, который соответствует элементу
     */
    private FieldElement getElement (Sprite sprite) {
        return table.getElement(sprite);
    }

    private SpeedVector.Axis getAxis(int side) {
        SpeedVector.Axis axis;
        if (side == CollisionBounds.LEFT_COLLISION || side == CollisionBounds.RIGHT_COLLISION) {
            axis = SpeedVector.Axis.Y;
        } else {
            axis = SpeedVector.Axis.X;
        }
        return axis;
    }
    
    private ArrayList<Sprite> getSystem(Map storage) {
        ArrayList<Sprite> result = new ArrayList<>();
        // Создание системы связанных соударившихся элементов
        return result;
    }
    
    private void handleSystem() {
        
    }
    
    @Override
    public void spritesCollided(SpritesCollidedEvent e) {
        ArrayList<Sprite> system;
        FieldElement element = getElement(e.activeSprite());
        FieldElement keyElement;
        // Столкновение произошло с границей
        if (e.passiveSprite() == null) {
            // Падение на нижнию границу элемента, отвечающего за успех игры
            if (element instanceof ChangingGameState && e.side() == CollisionBounds.BOTTOM_COLLISION) {
                ((ChangingGameState)element).handleChangingGameState();
            } else if (element instanceof Bouncing) {
                // Столкновение с разрешенными границами
                element.setRightPosition(e.xBound());
                if (e.xBound() == 0 ) {
                    ((Bouncing)element).handleCollision(getAxis(e.side()), null);
                } else {
                    element.setSpeed(new SpeedVector());
                }
                
            } else {
                //Столкновение не отскакивающих элементов
                element.setRightPosition(e.xBound());
                element.setSpeed(new SpeedVector());
            }   
        } else {
            Map passive = e.passiveSprite();
            // Выявить систему(если шарики в круге)
            system = getSystem(passive);
            // Если есть система
            if (!system.isEmpty()) {
                // Обработать столкновения внутри системы
                handleSystem();
                // Удалить спрайты, входящие в систему
                for ( Object keySprite : passive.keySet()) {
                    if (system.contains((Sprite)keySprite)) {
                        passive.remove((Sprite)keySprite);
                    }
                }
            }
            // Обработать спрайты вне системы
            for ( Object keySprite : passive.keySet()) {
                keyElement = table.getElement((Sprite) keySprite);
                Sprite[] valueSprites = (Sprite[]) passive.get(keySprite);
                for (Sprite value:valueSprites) {
                    CollisionRect intersectionRect = CollisionManager.getIntersectionRect(((Sprite)keySprite).getX(), ((Sprite)keySprite).getY(), ((Sprite)keySprite).getWidth(), ((Sprite)keySprite).getHeight(), value.getX(), value.getY(), value.getWidth(), value.getHeight());
                    FieldElement originObject = table.getElement(value);
                    FieldElement cloneKeyElement = keyElement.clone();
                    FieldElement cloneValueElement = originObject.clone();

                    originObject.handleCollision(cloneKeyElement);
                    keyElement.handleCollision(cloneValueElement);
                    table.deletePair(cloneKeyElement);
                    table.deletePair(cloneValueElement);
                }
            }
        }
    }
}
