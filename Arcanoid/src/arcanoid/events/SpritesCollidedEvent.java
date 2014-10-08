/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcanoid.events;

import com.golden.gamedev.object.Sprite;
import java.util.EventObject;
import java.util.Map;

/**
 * Класс обработки события столкновения объектов
 * 
 * @author Мария
 */
public class SpritesCollidedEvent extends EventObject{

    /** Спрайт инициирующий столкновение */
    private Sprite activeSprite;
    /** Пассивные спрайты */
    private Map<Sprite, Sprite> passiveSprites;
    /**
     * Конструктор
     * @param source ресурсы
     * @param activeSprite активный спрайт
     * @param passiveSprites спрайты, с которыми ударились
     */
    public SpritesCollidedEvent(Object source, Sprite activeSprite, Map<Sprite, Sprite> passiveSprites) {
        super(source);
        this.activeSprite = activeSprite;
        this.passiveSprites = passiveSprites;
    }
    
    /**
     * Получить активный спрайт
     * @return активный спрайт
     */
    public Sprite activeSprite() {
        
    }
    
    /**
     * Получить массив спрайтов, с которыми столкнулись
     * @return массив спрайтов, с которыми столкнулись
     */
    public Map<Sprite, Sprite> passiveSprite() {
        
    }
}
