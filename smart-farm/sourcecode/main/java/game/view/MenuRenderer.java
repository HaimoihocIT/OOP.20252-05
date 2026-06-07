package game.view;

import game.GameContext;
import game.graphics.Sprite;

import static game.GameConstants.*;


public class MenuRenderer extends BaseRenderer {

    @Override
    public void render(GameContext ctx) {
        ctx.screen.renderSprite(0, 0, Sprite.bgBlur, false);

        // Title board
        ctx.screen.renderSprite(46, 15, Sprite.woodenBoard, false);
        ctx.guiFont.render(ctx.screen, "SMART FARM SIMULATOR", 80, 27, COLOR_WHITE, 2, true, false);

        if (ctx.hasActiveGame) {
            renderButton(ctx, 155, 70,  "CONTINUE");
            renderButton(ctx, 155, 100, "NEW GAME");
            renderButton(ctx, 155, 130, "HELP");
            renderButton(ctx, 155, 160, "QUIT");
        } else {
            renderButton(ctx, 155, 70,  "START GAME");
            renderButton(ctx, 155, 105, "HELP");
            renderButton(ctx, 155, 140, "QUIT");
            ctx.guiFont.render(ctx.screen, "PRESS ENTER TO START", 140, 175, COLOR_WHITE, 1, true, false);
        }

        if (ctx.showQuitConfirm) {
            ctx.screen.fillRect(100, 85, 200, 40, COLOR_OVERLAY_BLACK);
            ctx.screen.renderOutline(100, 85, 200, 40, COLOR_OUTLINE_WHITE);
            ctx.guiFont.render(ctx.screen, "REALLY QUIT?",       165, 95,  COLOR_WHITE,     1, true, false);
            ctx.guiFont.render(ctx.screen, "Y = YES   ESC = NO", 150, 110, COLOR_TEXT_GRAY, 1, true, false);
        }
    }


}
