package game.view;

import game.GameContext;
import game.graphics.Sprite;

import static game.GameConstants.*;


public class HelpRenderer extends BaseRenderer {

    @Override
    public void render(GameContext ctx) {
        ctx.screen.renderSprite(0, 0, Sprite.bgBlur, false);
        ctx.screen.renderSprite(46, 35, Sprite.helpBoard, false);

        // Title board
        ctx.screen.renderSprite(155, 5, Sprite.titleBoard, false);
        ctx.guiFont.render(ctx.screen, "HELP", 188, 15, COLOR_WHITE, 1, true, false);

        int y = 50;
        int x = 60;

        ctx.guiFont.render(ctx.screen, "1. LEFT CLICK to use selected tool", x, y, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "2. Keys 1-5 to select tool", x, y += 15, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "3. P key to open Seed Shop", x, y += 15, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "4. ENTER or Adv Day button to end turn", x, y += 15, 0xff000000, 1, false,
                false);
        ctx.guiFont.render(ctx.screen, "5. ESC to open menu", x, y += 15, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "6. Harvest only MATURE crops", x, y += 15, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "7. Plant needs water EVERY DAY", x, y += 15, 0xff000000, 1, false, false);
        ctx.guiFont.render(ctx.screen, "8. S=Sunny  D=Drought  R=Rainy  L=Bug", x, y += 15, 0xff000000, 1, false,
                false);
        // Back Button
        ctx.screen.renderSprite(155, 190, Sprite.smallWoodenBoard, false);
        ctx.guiFont.render(ctx.screen, "BACK", 188, 200, COLOR_WHITE, 1, true, false);
    }
}
