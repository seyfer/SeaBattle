package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Artificial intelligence
 */
public class Robot
{

    public ShipField field;
    public int x, y;
    Random rand;

    public Robot(ShipField field) {
        this.field = field;
        this.rand = new Random();
    }

    public boolean tryShot() {
        ArrayList<Element> list = new ArrayList<Element>();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Element element = field.elements[i][j];
                if (!element.shuted) {
                    list.add(element);
                }
            }
        }
        Element e = list.get(rand.nextInt(list.size()));
        return field.doShot(e.x, e.y);
    }

    public boolean move() {
        boolean found = false;
        // search for injured the ship item
        first:
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Element element = field.elements[i][j];
                if (element.state == ElementState.INJURED) {
                    // if you found injured the ship item
                    found = true;
                    x = i;
                    y = j;
                    break first;
                }
            }
        }

        return tryShot();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
