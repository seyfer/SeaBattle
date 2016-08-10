package ui;

import javax.swing.*;
import java.awt.*;

public abstract class PanelField extends JPanel implements Subscriber
{
    protected GameModel model;

    public PanelField(GameModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < 11; i++) {
            graphics.drawLine(i * 15, 0, i * 15, 150);
            graphics.drawLine(0, i * 15, 150, i * 15);
        }

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                paintElement(graphics, i, j);
            }
        }

    }

    protected abstract void paintElement(Graphics graphics, int i, int j);

    @Override
    public void update() {
        this.repaint();
    }
}
