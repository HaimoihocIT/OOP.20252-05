package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import static game.GameConstants.*;

public class HelpController implements StateUpdater{
    @Override
    public void update(GameContext ctx, InputManager inputManager) {
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        // Back button click
        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")) {
            if (mx >= BTN_CENTER_X && mx <= BTN_CENTER_X + BTN_SMALL_W && my >= HELP_BACK_Y && my <= HELP_BACK_Y + BTN_SMALL_H) {
                ctx.handler.setState(GameState.MENU);
            }
        }

        if(inputManager.isJustPressed(ctx.keyboard.escape, "escapeTriggered")){
            ctx.handler.setState(GameState.MENU);
        }
    }
}
