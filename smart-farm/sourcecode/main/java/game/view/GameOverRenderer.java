package game.view;

import game.GameContext;
import game.graphics.Sprite;

import static game.GameConstants.*;


public class GameOverRenderer extends BaseRenderer {

    @Override
    public void render(GameContext ctx) {
        ctx.screen.renderSprite(0, 0, Sprite.bgBlur, false);

        // Draw "GAME OVER" board
        ctx.screen.renderSprite(46, 50, Sprite.woodenBoard, false);
        ctx.guiFont.render(ctx.screen, "GAME OVER", 128, 60, COLOR_WHITE, 2, true, false);

        // Buttons
        renderButton(ctx, 155, 120, "PLAY AGAIN");
        renderButton(ctx, 155, 155, "EXIT");
    }


}
