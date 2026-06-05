package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import static game.GameConstants.*;

public class GameOverController implements StateUpdater {
    @Override
    public void update(GameContext ctx, InputManager inputManager) {
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")) {
            if (mx >= BTN_CENTER_X && mx <= BTN_CENTER_X + BTN_SMALL_W) {
                if (my >= GAMEOVER_PLAY_AGAIN_Y && my <= GAMEOVER_PLAY_AGAIN_Y + BTN_SMALL_H) {
                    ctx.reset();
                    ctx.handler.setState(GameState.GAME);
                } else if (my >= GAMEOVER_EXIT_Y && my <= GAMEOVER_EXIT_Y + BTN_SMALL_H) {
                    ctx.hasActiveGame = false;
                    ctx.handler.setState(GameState.MENU);
                }
            }
        }
    }
}
