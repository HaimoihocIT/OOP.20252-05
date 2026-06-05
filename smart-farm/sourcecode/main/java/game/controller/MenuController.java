package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import static game.GameConstants.*;

public class MenuController implements StateUpdater{
    @Override
    public void update(GameContext ctx, InputManager inputManager){
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        if (ctx.showQuitConfirm) {
            if (ctx.keyboard.kY) System.exit(0);
            if (inputManager.isJustPressed(ctx.keyboard.escape, "escapeTriggered")) {
                ctx.showQuitConfirm = false;
            }
            return;
        }

        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")){
            if (mx >= BTN_CENTER_X && mx <= BTN_CENTER_X + BTN_SMALL_W) {
                if (ctx.hasActiveGame){
                    // 4-button layout: CONTINUE(70), NEW GAME(100), HELP(130), QUIT(160)
                    if (my >= MENU_4BTN_CONTINUE_Y && my <= MENU_4BTN_CONTINUE_Y + BTN_SMALL_H){
                        ctx.handler.setState(GameState.GAME);
                    }
                    else if (my >= MENU_4BTN_NEWGAME_Y && my <= MENU_4BTN_NEWGAME_Y + BTN_SMALL_H){
                        ctx.reset(); ctx.handler.setState(GameState.GAME);
                    }
                    else if (my >= MENU_4BTN_HELP_Y && my <= MENU_4BTN_HELP_Y + BTN_SMALL_H){
                        ctx.handler.setState(GameState.HELP);
                    }
                    else if (my >= MENU_4BTN_QUIT_Y && my <= MENU_4BTN_QUIT_Y + BTN_SMALL_H){
                        ctx.showQuitConfirm = true;
                    }
                } else {
                    // 3-button layout: START GAME(70), HELP(105), QUIT(140)
                    if (my >= MENU_3BTN_START_Y && my <= MENU_3BTN_START_Y + BTN_SMALL_H){
                        ctx.reset(); ctx.handler.setState(GameState.GAME);
                    }
                    else if (my >= MENU_3BTN_HELP_Y && my <= MENU_3BTN_HELP_Y + BTN_SMALL_H){
                        ctx.handler.setState(GameState.HELP);
                    }
                    else if (my >= MENU_3BTN_QUIT_Y && my <= MENU_3BTN_QUIT_Y + BTN_SMALL_H){
                        ctx.showQuitConfirm = true;
                    }
                }
            }
        }

        if (inputManager.isJustPressed(ctx.keyboard.enter, "enterTriggered")){
            if(ctx.hasActiveGame){
                ctx.handler.setState(GameState.GAME);
            } else{
                ctx.reset();
                ctx.handler.setState(GameState.GAME);
            }
        }
    }
}
