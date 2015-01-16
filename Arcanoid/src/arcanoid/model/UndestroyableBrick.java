/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoid.model;

import arcanoid.service.Buffer;
import com.golden.gamedev.object.Sprite;


/**
 *
 * @author Елена
 */
public class UndestroyableBrick extends Brick implements Bounced {

    public UndestroyableBrick(Buffer table) {
        super(table);
    }

    public UndestroyableBrick clone() {
        UndestroyableBrick brick = new UndestroyableBrick(this.table);
        Sprite sprite = table.getSprite(this);
        table.addPair(brick, sprite);
        brick.copy(this);
        return brick;
    }
}
