package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship
{
    public int x, y;
    private int dx, dy;
    public int size;
    public int health;
    public ShipState state;
    public ShipField field;
    public List<Element> elements;

    public Ship(ShipField field, int size) {
        this.size = size;
        this.health = size;
        this.field = field;
        this.state = ShipState.WALL;

        do {
            this.placeRandomly();
        } while (!this.checkPlace());

        this.elements = new ArrayList<Element>();
        this.setShip();
    }

    private void placeRandomly() {
        Random rand = new Random();
        this.x = rand.nextInt(10);
        this.y = rand.nextInt(10);
        this.dx = 0;
        this.dy = 0;
        if (rand.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
    }

    private boolean passShip(TrigerShip trigerShip) {
        int i, m, n;

        // ship
        for (i = 0; i < size; i++) {
            m = y + i * dy;
            n = x + i * dx;
            if (trigerShip.isShip(m, n) == false) {
                return false;
            }
        }
        // area on top and bottom of the ship
        for (i = 0; i < size; i++) {
            m = y + i * dy - dx;
            n = x + i * dx - dy;
            if (trigerShip.isBorder(m, n) == false) {
                return false;
            }
            m = y + i * dy + dx;
            n = x + i * dx + dy;
            if (trigerShip.isBorder(m, n) == false) {
                return false;
            }
        }
        // area on the left and right side of the ship
        for (i = -1; i < 2; i++) {
            m = y + i * dx - dy;
            n = x + i * dy - dx;
            if (trigerShip.isBorder(m, n) == false) {
                return false;
            }
            m = y + i * dx + (dy * size);
            n = x + i * dy + (dx * size);
            if (trigerShip.isBorder(m, n) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPlace() {
        return passShip(new TrigerShipCheck(this));
    }

    private void setShip() {
        passShip(new TrigerShipSet(this));
    }
}
