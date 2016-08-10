package logic;

public class TrigerShipCheck extends TrigerShip
{

    public TrigerShipCheck(Ship ship) {
        super(ship);
    }

    @Override
    public boolean isShip(int m, int n) {
        ElementState state = field.getElement(m, n);
        return (state == ElementState.WATER);
    }

    @Override
    public boolean isBorder(int m, int n) {
        ElementState state = field.getElement(m, n);
        return (state == ElementState.BORDER)
                || (state == ElementState.WATER)
                || (state == ElementState.EMPTY);
    }
}
