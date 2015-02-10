/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.events.CollisionHandleEndEvent;
import arcanoid.events.CollisionHandleEndListener;
import arcanoid.events.SpritesCollidedEvent;
import arcanoid.events.SpritesCollidedListener;
import arcanoid.service.Buffer;
import arcanoid.service.SpeedVector;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import com.golden.gamedev.object.collision.CollisionRect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обработчик столкновений
 * (в этом классе не получается сделать приватный класс для слушателя,
 * так как создается соединение с элементом SpritesCollidedListener в
 * классе GameFieldView)
 * @author Елена
 */
public class CollisionHandler implements SpritesCollidedListener{
    /** Таблица соответствий элемента поля со спрайтом */
    private Buffer table;
    /** Блокировка стокновений, пока элемент не выйдет из гарницы другого*/
    private boolean block = false;
    /** Слушатели конца обработки ДЛЯ ТЕСТОВ*/
    private ArrayList<CollisionHandleEndListener> listeners;
    
    /**
     * Констурктор
     * @param table буффер
     */
    public CollisionHandler (Buffer table) {
        this.table = table;
        listeners = new ArrayList<>();
    }
    
    /**
     * Добавить слушателя (ДЛЯ ТЕСТОВ)
     * @param listener слкшатель
     */
    public void addHandleEndListener(CollisionHandleEndListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Испустить событие о том, что мяч упал за нижнюю границу (ДЛЯ ТЕСТОВ)
     * 
     * @param first первый элемент
     * @param second второй элемент
     * @param third третий элемент
     */
    private void fireHandleEnd(FieldElement first, FieldElement second, FieldElement third) {
        for (CollisionHandleEndListener listener: listeners) {
            listener.checkAssertion(new CollisionHandleEndEvent(this,first,second, third));
        }
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

    /**
     * Получить ось для отскока от стороны
     * 
     * @param side сторона
     * @return ось
     */
    private SpeedVector.Axis getAxis(int side) {
        SpeedVector.Axis axis;
        if (side == CollisionBounds.LEFT_COLLISION || side == CollisionBounds.RIGHT_COLLISION) {
            axis = SpeedVector.Axis.Y;
        } else {
            axis = SpeedVector.Axis.X;
        }
        return axis;
    }
    
    /**
     * Сформировать список элементов, с которыми стокнулся по цепочке
     * @param sprite спрайт, для которого определяем
     * @param storage хранилище со столкновениями
     * @param list список, куда заносится цепочка столкновений
     */
    private void formListForElement(Sprite sprite, Map storage, ArrayList<Sprite> list) {
        Set keySet = storage.keySet();
        if (keySet.contains(sprite)) {
            Sprite[] valueSprites = (Sprite[]) storage.get(sprite);
            for (Sprite value:valueSprites) {
                if (!list.contains(value)) {
                    list.add(value);
                    formListForElement(value, storage, list);
                }
            }
        }
    }
    
    /**
     * Получить систему, образованную несколькими соударившимися элементами
     * 
     * @param storage хранилище соударений
     * @return список, содержащий элементы, входящие в систему
     */
    private ArrayList<Sprite> getSystem(Map storage) {
        ArrayList<Sprite> result = new ArrayList<>();
        // Создание системы связанных соударившихся элементов
        for ( Object keySprite : storage.keySet()) {
            result.add((Sprite)keySprite);
            formListForElement((Sprite)keySprite, storage, result);
            // Есть система, а не простое соударение
            if (result.size() >= 3) {
                return result;
            } else {
                result = new ArrayList<>();
            }
        }
        
        return result;
    }
    
    /**
     * Обработать столкновение с системой
     * 
     * @param system система
     */
    private void handleSystem(ArrayList<Sprite> system) {
        ArrayList<FieldElement> cloneElements = new ArrayList<>();
        ArrayList<FieldElement> elements = new ArrayList<>();
        // Карта элемент-> клоны, столкнувшихся элементов, которые должнв рассматриваться как один элемент
        HashMap<FieldElement, ArrayList<FieldElement>> elementsAsOne = new HashMap<>();
        // Получение элементов и клонов
        for (Sprite sprite:system) {
            FieldElement element = table.getElement(sprite);
            if (element instanceof Bouncing) {
                elements.add(element);
                cloneElements.add(element.clone());
            }
        }
        // Создание карты
        for (FieldElement element:elements) {
            ArrayList<FieldElement> elementArray = new ArrayList<>();
            for (FieldElement other:cloneElements) {
                if (elements.indexOf(element) != cloneElements.indexOf(other)) {
                    elementArray.add(other);
                }
            }
            elementsAsOne.put(element, elementArray);
        }
        // Обработка каждого элемента системы
        for (Map.Entry<FieldElement, ArrayList<FieldElement>> entrySet : elementsAsOne.entrySet()) {
            try {
                FieldElement key = entrySet.getKey();
                ArrayList<FieldElement> values = entrySet.getValue();
                // Создание элемента на основе нескольких
                Class classElement = values.get(0).getClass();
                Constructor[] construct =  classElement.getDeclaredConstructors();
                Object otherElement = construct[0].newInstance();
                SpeedVector resultSpeed = new SpeedVector();
                for (FieldElement value: values) {
                    resultSpeed = resultSpeed.sum(value.speed());
                }
                ((FieldElement)otherElement).setSpeed(resultSpeed);
                key.handleCollision((FieldElement)otherElement);
            } catch (InstantiationException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CollisionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Обработать столкновение спрайтов
     * 
     * @param e событие столкновения спрайтов
     */
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
                handleSystem(system);
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
                    if (!system.contains(value)) {
                        CollisionRect intersectionRect = CollisionManager.getIntersectionRect(((Sprite)keySprite).getX(), ((Sprite)keySprite).getY(), ((Sprite)keySprite).getWidth(), ((Sprite)keySprite).getHeight(), value.getX(), value.getY(), value.getWidth(), value.getHeight());
                        FieldElement originObject = table.getElement(value);
                        // Склонировать элементы
                        FieldElement cloneKeyElement = keyElement.clone();
                        FieldElement cloneValueElement = originObject.clone();
                        double width = originObject.size().width();
                        double height = originObject.size().height();
                        if (width > keyElement.size().width()) {
                            width = keyElement.size().width();
                        }
                        if (height > keyElement.size().height()) {
                            height = keyElement.size().height();
                        }
                        if (block && intersectionRect.width < width/1.5 && intersectionRect.height < height/1.5) {
                            block = false;
                        }
                        if (!block) {
                            // Обработать столкновение каждым элементом
                            originObject.handleCollision(cloneKeyElement);
                            keyElement.handleCollision(cloneValueElement);
                            fireHandleEnd(keyElement, originObject, null);
                        }
                        if (intersectionRect.width > width/2 && intersectionRect.height > height/2) {
                            block = true;
                        }
                        table.deletePair(cloneKeyElement);
                        table.deletePair(cloneValueElement);
                    }
                }
            }
        }
    }
}
