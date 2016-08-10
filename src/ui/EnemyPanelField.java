package ui;

import logic.ElementState;

import java.awt.*;

public class EnemyPanelField extends PanelField
{

    public EnemyPanelField(GameModel model) {
        super(model);
    }

    private Color getColorByStateElement(ElementState state) {
        switch (state) {
            case BORDER:
                return new Color(225, 225, 255);
            case WATER:
                return new Color(225, 225, 255);
            case WALL:
                return new Color(225, 225, 255);
            case INJURED:
                return Color.red;
            case KILLED:
                return Color.gray;
            case MISSED:
                return Color.black;
        }

        return Color.blue;
    }

    @Override
    protected void paintElement(Graphics graphics, int i, int j) {
        ElementState state = model.enemyPlayerField.getElement(i, j);
        graphics.setColor(getColorByStateElement(state));
        if (state == ElementState.MISSED) {
            graphics.fillRect(i * 15 + 6, j * 15 + 6, 4, 4);
        } else {
            graphics.fillRect(i * 15 + 1, j * 15 + 1, 14, 14);
        }
    }

}
