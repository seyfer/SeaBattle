package logic;

public class Element
{
    public ElementState state;
    public Ship ship;
    public boolean shuted;
    public int x, y;

    public Element(int x, int y) {
        this.state = ElementState.WATER;
        this.shuted = false;
        this.x = x;
        this.y = y;
    }
}
