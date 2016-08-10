package ui;

import logic.Ship;
import logic.ShipState;

import javax.swing.*;
import java.awt.*;

public class ScoreField extends JPanel implements Subscriber
{

    private static final String congratulation = "You are winner!!!";
    private GameModel model;
    private int ships;

    public ScoreField(GameModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] elem = new int[4];
        for (int i = 0; i < 0; i++) {
            elem[i] = 0;
        }

        ships = 0;
        for (Ship ship : model.enemyPlayerField.ships) {
            if (ship.state != ShipState.KILLED) {
                elem[ship.size - 1]++;
                ships++;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (i + 1); j++) {
                g.setColor(Color.gray);
                g.fillRect(j * 15 + 10, i * 15 + 5, 13, 13);
            }
            g.setColor(Color.black);
            g.drawString(String.valueOf(elem[i]), 75, i * 15 + 16);
        }

        String string = "Alive: ".concat(String.valueOf(ships));
        g.drawString(string, 25, 100);
        if (ships == 0) {
            congratTheWinner();
        }
    }

    private void congratTheWinner() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("Congratulations!");
        frame.setResizable(false);
        frame.setBounds(1000, 300, 750, 150);

        JTextArea textArea = new JTextArea(congratulation);
        textArea.setBackground(Color.magenta);

        Font font = new Font("Times New Roman", 0, 100);
        textArea.setFont(font);
        frame.getContentPane().add(textArea);
    }

    @Override
    public void update() {
        this.repaint();
    }

}
