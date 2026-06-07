package game.view;

import game.GameContext;
import game.graphics.Sprite;

import static game.GameConstants.*;

public abstract class BaseRenderer implements StateRenderer {

    protected void renderButton(GameContext ctx, int x, int y, String text) {
        ctx.screen.renderSprite(x, y, Sprite.smallWoodenBoard, false);
        int textX = x + (90 - text.length() * 6) / 2;
        ctx.guiFont.render(ctx.screen, text, textX, y + 10, COLOR_WHITE, 1, true, false);
    }

    @Override
    public abstract void render(GameContext ctx);
}
