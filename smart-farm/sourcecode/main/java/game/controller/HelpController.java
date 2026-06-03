package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;

public class HelpController implements StateUpdater{
    @Override
    public void update(GameContext ctx, InputManager inputManager) {
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        // Back button click
        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")) {
            if (mx >= 155 && mx <= 155 + 90 && my >= 190 && my <= 190 + 27) {
                ctx.handler.setState(GameState.MENU);
            }
        }

        if(inputManager.isJustPressed(ctx.keyboard.escape, "escapeTriggered")){
            ctx.handler.setState(GameState.MENU);
        }
    }
}
