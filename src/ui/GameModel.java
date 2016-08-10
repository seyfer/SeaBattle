package ui;

import logic.Robot;
import logic.ShipField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel
{
    private List<Subscriber> listeners = new ArrayList<Subscriber>();
    public ShipField myPlayerField;
    public ShipField enemyPlayerField;
    public Robot robot;
    public int currentPlayer;

    public GameModel() {
        this.currentPlayer = 0;
        myPlayerField = new ShipField();
        enemyPlayerField = new ShipField();
        robot = new Robot(myPlayerField);
    }

    public void startNewGame() {
        myPlayerField.putShip();
        enemyPlayerField.putShip();
        updateSubscribers();
    }

    public void doShotByOpponent(int x, int y) {
        // If a player goes 
        if (currentPlayer == 0) {
            // if player didn't hit
            if (enemyPlayerField.doShot(x, y) == false) {
                currentPlayer = 1;
            }
        }

        // if enemy goes
        if (currentPlayer == 1) {
            while (robot.move()) ;
            currentPlayer = 0;
        }
        updateSubscribers();
    }

    /**
     * registration of items that will be updated through repainting the data on
     * the form
     *
     * @param subscriber
     */
    public void register(Subscriber subscriber) {
        listeners.add(subscriber);
        subscriber.update();
    }

    public void unRegister(Subscriber subscriber) {
        listeners.remove(subscriber);
    }

    /**
     * data representation repaint
     */
    public void updateSubscribers() {
        Iterator<Subscriber> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            Subscriber subscriber = (Subscriber) iterator.next();
            subscriber.update();
        }
    }
}
