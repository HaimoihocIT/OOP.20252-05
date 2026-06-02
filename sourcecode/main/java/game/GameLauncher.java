package game;

import javax.swing.JFrame;
import java.awt.Dimension;

import static game.GameConstants.*;

public class GameLauncher {
    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("Smart Farm Simulator");
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
