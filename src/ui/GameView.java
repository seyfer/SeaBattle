package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame
{

    private GameModel model;
    private GameController controller;

    private JMenuItem newGame;
    private JMenuItem exit;
    private JMenuItem about;

    private MyPanelField myPlayerPanel;
    private EnemyPanelField enemyPlayerPanel;
    private ScoreField scorePanel;

    GameView(GameModel model) {
        this.model = model;
        buildUI();
        this.model.register(myPlayerPanel);
        this.model.register(enemyPlayerPanel);
        this.model.register(scorePanel);
        this.controller = new GameController(this, model);
        attachController();
    }

    public void update() {
        myPlayerPanel.repaint();
        enemyPlayerPanel.repaint();
        scorePanel.repaint();
    }

    private void attachController() {
        about.addActionListener(controller);
        newGame.addActionListener(controller);
        exit.addActionListener(controller);

        enemyPlayerPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent arg) {
                controller.mousePressed(arg);
            }
        });
    }

    private void buildUI() {
        this.setTitle("SeaBattle");
        this.setResizable(false);
        this.setBounds(400, 300, 483, 228);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 500, 20);
        this.getContentPane().add(menuBar);

        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        newGame = new JMenuItem("New Game");
        gameMenu.add(newGame);

        exit = new JMenuItem("Exit");
        gameMenu.add(exit);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        about = new JMenuItem("About");
        helpMenu.add(about);

        myPlayerPanel = new MyPanelField(model);
        myPlayerPanel.setBounds(20, 31, 151, 151);
        this.getContentPane().add(myPlayerPanel);

        enemyPlayerPanel = new EnemyPanelField(model);
        enemyPlayerPanel.setBounds(190, 31, 151, 151);
        this.getContentPane().add(enemyPlayerPanel);

        scorePanel = new ScoreField(model);
        scorePanel.setBounds(370, 31, 90, 151);
        scorePanel.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(scorePanel);
    }
}
