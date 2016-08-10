package ui;

public class GameLauncher
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        view.setVisible(true);
    }

}
