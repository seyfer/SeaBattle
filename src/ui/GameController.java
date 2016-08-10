package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GameController implements ActionListener
{

    private static final String gameRules = "В игру \"морской бой\" играют два человека,\n "
            + "которые по очереди называют координаты\n кораблей на карте противника.\n "
            + "\nЕсли координаты заняты, то корабль или\n часть его \"топится\", а попавший имеет право\n сделать ещё один ход.";
    private GameModel model;
    private GameView view;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("New Game".equals(command)) {
            model.startNewGame();
        }
        if ("About".equals(command)) {
            JFrame rules = new JFrame();
            rules.setVisible(true);
            rules.setTitle("Game Rules");
            rules.setResizable(false);
            rules.setBounds(1000, 300, 800, 600);

            JTextArea textArea = new JTextArea(gameRules);
            textArea.setBackground(Color.CYAN);

            Font font = new Font("Times New Roman", 0, 40);
            textArea.setFont(font);
            rules.getContentPane().add(textArea);
        }
        if ("Exit".equals(command)) {
            System.exit(0);
        }
    }

    public void mousePressed(MouseEvent arg0) {
        int x = arg0.getX() / 15;
        int y = arg0.getY() / 15;
        model.doShotByOpponent(x, y);
    }

}
