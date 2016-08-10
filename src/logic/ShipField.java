package logic;

import java.util.ArrayList;
import java.util.List;

public class ShipField
{

    public Element[][] elements;
    public List<Ship> ships;

    /**
     * creation of fields with ships
     */
    public ShipField() {
        // fill in the field by water elements
        elements = new Element[10][10];
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                elements[i][j] = new Element(i, j);
            }
        }
        this.putShip();
    }

    /**
     * fill in the field by water and place the ships
     */
    public void putShip() {
        // fill in the field by water
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Element element = elements[i][j];
                element.state = ElementState.WATER;
                element.shuted = false;
            }
        }

        // fill in the field by ships
        ships = new ArrayList<Ship>();
        for (int i = 4; i > 0; i--) {
            for (int j = (5 - i); j > 0; j--) {
                Ship ship = new Ship(this, i);
                ships.add(ship);
            }
        }

        // remove the ship environment
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Element element = elements[i][j];
                if (element.state == ElementState.BORDER) {
                    element.state = ElementState.WATER;
                }
            }
        }

    }

    public boolean doShot(int x, int y) {
        boolean shot = false;

        ElementState state = this.getElement(x, y);
        elements[x][y].shuted = true;
        if (state == ElementState.WALL) {
            shot = true;
            Ship ship = elements[x][y].ship;
            if (ship.health != 0) {
                ship.health--;
                if (ship.health == 0) {
                    ship.state = ShipState.KILLED;
                    for (Element element : ship.elements) {
                        element.state = ElementState.KILLED;
                    }
                } else {
                    ship.state = ShipState.INJURED;
                    elements[x][y].state = ElementState.INJURED;
                }
            }
        } else if ((state == ElementState.BORDER)
                || (state == ElementState.WATER)) {
            this.setElement(x, y, ElementState.MISSED);
        }
        return shot;
    }

    public boolean isBound(int x, int y) {
        return !((x < 0) || (x > 9) || (y < 0) || (y > 9));
    }

    public ElementState getElement(int x, int y) {
        if (isBound(x, y)) {
            return elements[x][y].state;
        } else {
            return ElementState.EMPTY;
        }
    }

    public boolean setElement(int x, int y, ElementState state) {
        if (isBound(x, y)) {
            elements[x][y].state = state;
        }
        return true;
    }
}
