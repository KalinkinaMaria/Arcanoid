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
    private Map passiveSprites;
    /** Сторона столкновения*/
    private int side;
    /** Граница по оси*/
    private int xBound;
    
    /**
     * Конструктор
     * @param source ресурсы
     * @param activeSprite активный спрайт
     * @param passiveSprites спрайты, с которыми ударились
     */
    public SpritesCollidedEvent(Object source, Sprite activeSprite, Map passiveSprites) {
        super(source);
        this.activeSprite = activeSprite;
        this.passiveSprites = passiveSprites;
        side = 0;
    }
    
    /**
     * Констурктор 
     * @param source источник
     * @param activeSprite активный спрайт
     * @param side сторона
     * @param xBound граница по оси
     */
    public SpritesCollidedEvent(Object source, Sprite activeSprite, int side, int xBound) {
        super(source);
        this.activeSprite = activeSprite;
        this.passiveSprites = null;
        this.side = side;
        this.xBound = xBound;
    }
    
    /**
     * Получить активный спрайт
     * @return активный спрайт
     */
    public Sprite activeSprite() {
        return activeSprite;
    }
    
    /**
     * Получить массив спрайтов, с которыми столкнулись
     * @return массив спрайтов, с которыми столкнулись
     */
    public Map passiveSprite() {
        return passiveSprites;
    }
    
    /**
     * Получить сторону столкновения
     * @return сторона столкновения
     */
    public int side() {
        return side;
    }
    
    /**
     * Получит границу по оси
     * @return граница по оси
     */
    public int xBound() {
        return xBound;
    }
}
