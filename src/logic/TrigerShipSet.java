package logic;

public class TrigerShipSet extends TrigerShip
{

    private Ship ship;

    public TrigerShipSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean isShip(int m, int n) {
        field.setElement(m, n, ElementState.WALL);
        ship.elements.add(field.elements[m][n]);
        field.elements[m][n].ship = ship;
        return true;
    }

    @Override
    public boolean isBorder(int m, int n) {
        field.setElement(m, n, ElementState.BORDER);
        return true;
    }
}
