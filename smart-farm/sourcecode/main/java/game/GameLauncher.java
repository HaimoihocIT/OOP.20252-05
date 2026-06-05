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

        game.ctx.scaleChangedCallback = () -> {
            Dimension size = new Dimension(SCREEN_WIDTH * game.ctx.scale, SCREEN_HEIGHT * game.ctx.scale);
            game.setPreferredSize(size);
            game.setMinimumSize(size);
            game.setMaximumSize(size);
            frame.pack();
            frame.setLocationRelativeTo(null);
        };

        game.start();
    }
}
