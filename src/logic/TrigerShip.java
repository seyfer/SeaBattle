package logic;

public abstract class TrigerShip
{
    public ShipField field;

    public TrigerShip(Ship ship) {
        this.field = ship.field;
    }

    public abstract boolean isShip(int m, int n);

    public abstract boolean isBorder(int m, int n);
}